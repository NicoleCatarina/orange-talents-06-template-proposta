package br.com.zupacademy.nicolecatarina.proposta.proposta.jobs;

import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.CartaoClient;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.GeracaoCartaoRequestGateway;
import br.com.zupacademy.nicolecatarina.proposta.proposta.Proposta;
import br.com.zupacademy.nicolecatarina.proposta.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CriarCartaoParaProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Transactional
    @Scheduled(cron = "0/5 * * * * *")
    public void reavaliarProposta() {
        //TODO      Refatorar para usar specification
        List<Proposta> propostas = propostaRepository.buscarPropostasComStatusElegivelQueNaoPossuemCartao();

        propostas.forEach(novaProposta -> {
            try {
                var geracaoCartaoRequest = new GeracaoCartaoRequestGateway(
                        String.valueOf(novaProposta.getId()),
                        novaProposta.getDocumento(),
                        novaProposta.getNome());

                var geracaoCartaoResponseGateway = cartaoClient.cadastrarCartao(geracaoCartaoRequest);
                var novoCartao = geracaoCartaoResponseGateway.toModel();
                novaProposta.vincularCartao(novoCartao);
            } catch (Exception ignored) {
            }
        });
    }

}
