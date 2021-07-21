package br.com.zupacademy.nicolecatarina.proposta.cartao.biometria;
import br.com.zupacademy.nicolecatarina.proposta.cartao.Cartao;
import br.com.zupacademy.nicolecatarina.proposta.cartao.CartaoRepository;
import br.com.zupacademy.nicolecatarina.proposta.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartoes/{id}/biometrias")
public class BiometriaController {

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity criarBiometria(@PathVariable("id") Long idCartao,
                                @Valid BiometriaRequest biometriaRequest,
                                UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe um cartão com id %d", idCartao)));

        Biometria biometria = biometriaRequest.toModel(cartao);
        Biometria novaBiometria = biometriaRepository.save(biometria);

        URI uriDoRecurso = uriComponentsBuilder
                .path("cartoes/{id}/biometrias/{id}")
                .build(cartao.getId(), novaBiometria.getId());

        return ResponseEntity.created(uriDoRecurso).build();
    }

}
