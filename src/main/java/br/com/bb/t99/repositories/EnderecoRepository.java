package br.com.bb.t99.repositories;

import br.com.bb.t99.models.Agenda;
import br.com.bb.t99.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
