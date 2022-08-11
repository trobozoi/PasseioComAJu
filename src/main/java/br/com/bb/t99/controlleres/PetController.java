package br.com.bb.t99.controlleres;

import br.com.bb.t99.models.Mensagem;
import br.com.bb.t99.dao.PetDao;
import br.com.bb.t99.exceptions.ErroSqlException;
import br.com.bb.t99.models.MensagemErro;
import br.com.bb.t99.models.Pet;
import br.com.bb.t99.services.PetService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.opentracing.Traced;
import org.springframework.stereotype.Controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Controller
@RequestScoped
@Path("/pet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Traced
public class PetController {

    Mensagem mensagem = null;
    MensagemErro erro = null;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private PetService service;

    @Inject
    private PetDao dao;

    public PetController(){}
    public PetController(PetDao dao, EntityManager em){
        this.dao = dao;
        this.em = em;
    }
    public PetController(PetDao dao){
        this.dao = dao;
    }

    public PetController(PetDao dao, PetService service){
        this.dao = dao;
        this.service = service;
    }

    @POST
    @Operation(summary = "Salvar um pet.",
            description = "Salvar um pet.")
    @APIResponse(
            responseCode = "201",
            description = "Pet",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Mensagem.class, type = SchemaType.OBJECT))})
    @APIResponse(
            responseCode = "422",
            description = "Erro ao salvar um pet",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class, type = SchemaType.OBJECT))})
    public Response salvar(@Valid Pet pet) {
        service.salvar(pet);
        mensagem = new Mensagem("Pet salvo com sucesso");
        return  Response.status(Response.Status.CREATED).entity(mensagem).build();
    }

    @GET
    @Operation(summary = "Listar os pets.",
            description = "Retorna uma lista dos pets cadastrados.")
    @APIResponse(
            responseCode = "200",
            description = "Pet",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class, type = SchemaType.ARRAY))})
    public Response getList() throws ErroSqlException {
        return Response.status(Response.Status.OK).entity(dao.buscaPets()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Listar Pet Por ID",
            description = "Retorna um pet de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Pet",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class, type = SchemaType.OBJECT))})
    @APIResponse(
            responseCode = "422",
            description = "Erro Negocial",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class, type = SchemaType.OBJECT))})
    public Response obtemPetsById(final @PathParam("id") Long id) throws Exception  {
        if(dao.loginPet(id) == null){
            erro = MensagemErro.PET_NOT_EXIST;
            return Response.status(422).entity(erro).build();
        }else{
            return Response.status(Response.Status.OK).entity(dao.loginPet(id).returnAttributes()).build();
        }
    }
}