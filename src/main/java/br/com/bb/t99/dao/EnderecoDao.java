package br.com.bb.t99.dao;

import br.com.bb.t99.exceptions.ErroSqlException;
import br.com.bb.t99.models.Endereco;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Traced
@RequestScoped
public class EnderecoDao {

    @PersistenceContext
    private EntityManager em;

    public EnderecoDao ()
    {
    }

    public EnderecoDao (EntityManager em)
    {
        this.em = em;
    }

    public List<Endereco> buscaEnderecoDaos() throws ErroSqlException {
        String nameQuery = "CONSULTAR_ENDERECO";

        TypedQuery<Endereco> query = em
                .createNamedQuery(nameQuery, Endereco.class);

        try {
            return query.getResultList();
        } catch (NoResultException e){
            return new ArrayList<>();
        } catch (PersistenceException e){
            throw new ErroSqlException(e);
        }
    }

    @Transactional
    public Endereco salvar(Endereco endereco) throws ErroSqlException {
        em.persist(endereco);
        return endereco;
    }

    @Transactional
    public Endereco loginAgenda(Long id) throws ErroSqlException {

        Endereco endereco = em.find(Endereco.class, id);

        return endereco;
    }
}