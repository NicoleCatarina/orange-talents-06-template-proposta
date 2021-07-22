package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartaoClient {

    @PostMapping
    GeracaoCartaoGatewayResponse cadastrarCartao(@RequestBody GeracaoCartaoGatewayRequest geracaoCartaoGatewayRequest);

    @PostMapping("/{id}/bloqueios")
    BloqueioCartaoGatewayReponse bloquearCartao(@PathVariable String id,
                                                @RequestBody BloqueioCartaoGatewayRequest bloqueioCartaoGatewayRequest);

    @PostMapping("/{id}/avisos")
    AvisoViagemGatewayResponse criarAvisoDeViagem(@PathVariable String id,
                                                  @RequestBody AvisoViagemGatewayRequest avisoViagemGatewayRequest);
}
