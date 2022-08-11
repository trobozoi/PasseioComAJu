package br.com.bb.t99.dao;

import br.com.bb.t99.exceptions.ErroSqlException;
import br.com.bb.t99.models.Pet;
import org.eclipse.microprofile.opentracing.Traced;
import org.springframework.cache.annotation.Cacheable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Traced
@RequestScoped
public class PetDao {

    @PersistenceContext
    private EntityManager em;

    public PetDao ()
    {
    }

    public PetDao (EntityManager em)
    {
        this.em = em;
    }

    @Cacheable
    public List<Pet> buscaPets() throws ErroSqlException {
        String nameQuery = "CONSULTAR_PET";

        TypedQuery<Pet> query = em
                .createNamedQuery(nameQuery, Pet.class);
        try {
            return query.getResultList();
        } catch (NoResultException e){
            return new ArrayList<>();
        } catch (PersistenceException e){
            throw new ErroSqlException(e);
        }
    }

    @Transactional
    public Pet salvar(Pet pet) throws ErroSqlException {

        //Vou inserir tudo maiúsculo.
        String nomeCliente = pet.getNome().toLowerCase();
        //Vou mudar só no retorno para o usuário
        //nomeCliente = WordUtils.capitalize(nomeCliente);

        pet.setNome(nomeCliente);

        em.persist(pet);

        return pet;
    }

    @Transactional
    public Pet loginPet(Long id) throws ErroSqlException {

        Pet pet = em.find(Pet.class, id);

        return pet;
    }
}