package br.com.bb.t99.repositories;

import br.com.bb.t99.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    //List<Agenda> findByDataContaining(LocalDateTime data);

    @Query("select a from Agenda a where a.data = :data order by p.id")
    List<Agenda> findAgenda(@Param("data") String data);
}
