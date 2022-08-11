package br.com.bb.t99.controlleres;

import br.com.bb.t99.dao.AgendaDao;
import br.com.bb.t99.exceptions.ErroSqlException;
import br.com.bb.t99.models.*;
import br.com.bb.t99.services.AgendaService;
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
@Path("/agenda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Traced
public class AgendaController {
    Mensagem mensagem = null;
    MensagemErro erro = null;
    @PersistenceContext
    private EntityManager em;

    @Inject
    private AgendaService service;

    @Inject
    private AgendaDao dao;

    @Inject
    private PetService petService;

    public AgendaController(){}
    public AgendaController(AgendaDao dao, EntityManager em){
        this.dao = dao;
        this.em = em;
    }

    public AgendaController(AgendaDao dao, EntityManager em, PetService petService){
        this.dao = dao;
        this.em = em;
        this.petService = petService;
    }


    public AgendaController(AgendaDao dao){
        this.dao = dao;
    }

    public AgendaController(AgendaDao dao, PetService petService){
        this.dao = dao;
        this.petService = petService;
    }

    public AgendaController(AgendaDao dao, AgendaService service){
        this.dao = dao;
        this.service = service;
    }

    public AgendaController(AgendaDao dao, AgendaService service, PetService petService){
        this.dao = dao;
        this.service = service;
        this.petService = petService;
    }

    @POST
    @Operation(summary = "Salvar um agendamento.",
            description = "Salvar um agendamento.")
    @APIResponse(
            responseCode = "201",
            description = "Agenda",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Mensagem.class, type = SchemaType.OBJECT))})
    @APIResponse(
            responseCode = "422",
            description = "Erro ao salvar um agendamento",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class, type = SchemaType.OBJECT))})
    public Response salvar(@Valid AgendaSimple agendaSimple) {
        Agenda agenda = new Agenda();
        agenda.setData(agendaSimple.getData());
        agenda.setDuracao(agendaSimple.getDuracao());
        Pet pet = petService.getOne(agendaSimple.getPet());
        agenda.setPet(pet);
        Agenda agenda1 = service.salvar(agenda);
        Response response = null;
        if(agenda1 != null) {
            mensagem = new Mensagem("Agendamento realizado com sucesso");
            response = Response.status(Response.Status.CREATED).entity(mensagem).build();
        } else if(agenda1 == null) {
            erro = MensagemErro.AGENDA_NAO_DISPONIVEL;
            response = Response.status(422).entity(erro).build();
        }
        return response;
    }

    @GET
    @Operation(summary = "Listar os agendamentos.",
            description = "Retorna uma lista dos agendamentos cadastrados.")
    @APIResponse(
            responseCode = "200",
            description = "Agenda",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Agenda.class, type = SchemaType.ARRAY))})
    public Response getList() throws ErroSqlException {
        return Response.status(Response.Status.OK).entity(dao.buscaAgendas()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Listar Agenda Por ID",
            description = "Retorna um agendamento de acordo com id solicitado")
    @APIResponse(
            responseCode = "200",
            description = "Agenda",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Agenda.class, type = SchemaType.OBJECT))})
    @APIResponse(
            responseCode = "422",
            description = "Erro Negocial",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class, type = SchemaType.OBJECT))})
    public Response obtemAgendassById(final @PathParam("id") Long id) throws Exception  {
        if(dao.loginAgenda(id) == null){
            erro = MensagemErro.AGENDA_NOT_EXIST;
            return Response.status(422).entity(erro).build();
        }else{
            return Response.status(Response.Status.OK).entity(dao.loginAgenda(id).returnAttributes()).build();
        }
    }
}