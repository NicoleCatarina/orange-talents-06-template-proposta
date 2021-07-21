package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.GeracaoCartaoGatewayResponse;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.GeracaoCartaoRequestGateway;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartaoClient {

    @PostMapping
    GeracaoCartaoGatewayResponse cadastrarCartao(@RequestBody GeracaoCartaoRequestGateway geracaoCartaoRequestGateway);

}
