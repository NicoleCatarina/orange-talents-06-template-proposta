package br.com.zupacademy.nicolecatarina.proposta.cartao.carteira;

import br.com.zupacademy.nicolecatarina.proposta.cartao.Cartao;
import br.com.zupacademy.nicolecatarina.proposta.cartao.CartaoRepository;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.CartaoClient;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.CarteiraGatewayRequest;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.CarteiraGatewayResponse;
import br.com.zupacademy.nicolecatarina.proposta.exception.EntidadeNaoEncontradaException;
import br.com.zupacademy.nicolecatarina.proposta.exception.RegraVioladaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes/{id}/carteiras")
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Transactional
    @PostMapping
    public ResponseEntity criar(@PathVariable Long id,
                                @RequestBody @Valid CarteiraRequest carteiraRequest) {

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe um cartão com id %d", id)));

        boolean cartaoJaAssociadoACarteira = cartao.jaEhAssociadoACarteira(carteiraRequest.getProvedorCarteira());

        if (cartaoJaAssociadoACarteira) {
            throw new RegraVioladaException(
                    String.format("O cartao %d já está vinculado a uma carteira com provedor %s",
                            cartao.getId(),
                            carteiraRequest.getProvedorCarteira()));
        }

        try {
            CarteiraGatewayRequest gatewayRequest = new CarteiraGatewayRequest(cartao.getEmailDoDono(), carteiraRequest.getProvedorCarteira());
            CarteiraGatewayResponse gatewayResponse = cartaoClient.criarCarteiraDigital(cartao.getNumero(), gatewayRequest);

//            if (gatewayResponse.getResultado().equals("ASSOCIADA")) {
            Carteira novaCarteira = new Carteira(gatewayResponse.getId(), cartao.getEmailDoDono(), gatewayRequest.getCarteira());
            cartao.associarCarteira(novaCarteira);

            return ResponseEntity.ok().build();
//            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
