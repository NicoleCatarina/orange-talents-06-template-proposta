package br.com.zupacademy.nicolecatarina.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long>,
                                            JpaSpecificationExecutor<Proposta> {

    boolean existsByDocumento(String documento);

    @Query("SELECT p from Proposta p left join Cartao c on p.id = c.idProposta where c.id is null and p.propostaEstado = 'ELEGIVEL'")
    List<Proposta> buscarPropostasComStatusElegivelQueNaoPossuemCartao();

}
