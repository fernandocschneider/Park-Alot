package br.edu.unoesc.parkalot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.parkalot.model.Pagamento;

@Repository
public interface pagamentoRepository extends JpaRepository<Pagamento, Long> {

}
