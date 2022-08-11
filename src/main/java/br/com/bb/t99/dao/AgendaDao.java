package br.com.bb.t99.dao;

import br.com.bb.t99.exceptions.ErroSqlException;
import br.com.bb.t99.models.Agenda;
import br.com.bb.t99.models.Pet;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.opentracing.Traced;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Traced
@RequestScoped
public class AgendaDao {

    @PersistenceContext
    private EntityManager em;

    public AgendaDao ()
    {
    }

    public AgendaDao (EntityManager em)
    {
        this.em = em;
    }
    @CacheResult(cacheName = "agendas")
    public List<Agenda> buscaAgendas() throws ErroSqlException {
        String nameQuery = "CONSULTAR_AGENDA";

        TypedQuery<Agenda> query = em
                .createNamedQuery(nameQuery, Agenda.class);

        try {
            return query.getResultList();
        } catch (NoResultException e){
            return new ArrayList<>();
        } catch (PersistenceException e){
            throw new ErroSqlException(e);
        }
    }

    @Transactional
    public Agenda salvar(Agenda agenda) throws ErroSqlException {
        em.persist(agenda);
        return agenda;
    }

    @Transactional
    public Agenda loginAgenda(Long id) throws ErroSqlException {

        Agenda agenda = em.find(Agenda.class, id);

        return agenda;
    }
}
