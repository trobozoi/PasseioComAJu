package br.com.bb.t99.services.impl;

import br.com.bb.dev.erros.exceptions.BBException;
import br.com.bb.t99.dao.AgendaDao;
import br.com.bb.t99.exceptions.ErroSqlException;
import br.com.bb.t99.models.Agenda;
import br.com.bb.t99.models.AgendaSimple;
import br.com.bb.t99.models.MensagemErro;
import br.com.bb.t99.repositories.AgendaRepository;
import br.com.bb.t99.services.AgendaService;
import io.quarkus.cache.CacheInvalidateAll;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaServiceImpl implements AgendaService {
    private final AgendaRepository agendaRepository;
    @Inject
    private AgendaDao agendaDao;

    @PersistenceContext
    private EntityManager em;

    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }
    /*
    public AgendaServiceImpl(AgendaRepository agendaRepository, AgendaDao agendaDao) {
        this.agendaRepository = agendaRepository;
        this.agendaDao = agendaDao;
    }*/

    @SneakyThrows
    @Override
    @CacheInvalidateAll(cacheName = "agendas")
    public Agenda salvar(Agenda agenda) {
        List<Agenda> agendaList;
        LocalDateTime dataIni = agenda.getData();
        LocalDateTime dataFim = agenda.getData().plusMinutes(agenda.getDuracao());
        try {
            agendaDao = new AgendaDao(em);
            agendaList = agendaDao.buscaAgendas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Agenda> agendaList1 = agendaList.stream().filter(
                agenda1 -> (dataFim.isAfter(agenda1.getData()) &&
                        (dataFim.isBefore(agenda1.getData().plusMinutes(agenda1.getDuracao())) ||
                                dataFim.isEqual(agenda1.getData().plusMinutes(agenda1.getDuracao())))) ||
                        ((dataIni.isAfter(agenda1.getData()) ||
                                dataIni.isEqual(agenda1.getData())) &&
                                dataIni.isBefore(agenda1.getData().plusMinutes(agenda1.getDuracao())))
        ).collect(Collectors.toList());

        if(agendaList1.size() != 0) {
            return null;
        }

        return agendaRepository.save(agenda);
    }
}