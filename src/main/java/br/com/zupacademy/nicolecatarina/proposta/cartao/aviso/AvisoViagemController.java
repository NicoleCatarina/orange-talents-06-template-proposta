package br.com.zupacademy.nicolecatarina.proposta.cartao.aviso;

import br.com.zupacademy.nicolecatarina.proposta.cartao.Cartao;
import br.com.zupacademy.nicolecatarina.proposta.cartao.CartaoRepository;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.AvisoViagemGatewayRequest;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.AvisoViagemGatewayResponse;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.CartaoClient;
import br.com.zupacademy.nicolecatarina.proposta.exception.EntidadeNaoEncontradaException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/cartoes/{id}/avisos-viagem")
public class AvisoViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CartaoClient cartaoClient;

    @Transactional
    @PostMapping
    public ResponseEntity criar(@PathVariable Long id,
                                @RequestBody @Valid AvisoViagemRequest avisoViagemRequest,
                                @RequestHeader HashMap<String, String> headers) {
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe um cartão com id %d", id)));

        //TODO
        //      Validar se o cartao já possui aviso de viagem no periodo informado, lançar RegraVioladaException caso possua

        AvisoViagemGatewayRequest gatewayRequest =
                new AvisoViagemGatewayRequest(avisoViagemRequest.getDestino(), avisoViagemRequest.getDataTermino());

        try {
            AvisoViagemGatewayResponse gatewayResponse = cartaoClient.criarAvisoDeViagem(cartao.getNumero(), gatewayRequest);

            String ipClienteDaRequisicao = request.getRemoteAddr();
            String userAgent = headers.get("user-agent");

            AvisoViagem novoAvisoDeViagem = avisoViagemRequest.toModel(ipClienteDaRequisicao, userAgent);
            cartao.associarAvisoDeViagem(novoAvisoDeViagem);
            return ResponseEntity.ok().build();
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}