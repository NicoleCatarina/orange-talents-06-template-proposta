package br.com.zupacademy.nicolecatarina.proposta.proposta;

import br.com.zupacademy.nicolecatarina.proposta.analisefinanceira.AnaliseFinanceiraClient;
import br.com.zupacademy.nicolecatarina.proposta.analisefinanceira.AnaliseFinanceiraRequest;
import br.com.zupacademy.nicolecatarina.proposta.exception.EntidadeNaoEncontradaException;
import br.com.zupacademy.nicolecatarina.proposta.proposta.evento.PropostaCriadaEvent;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseFinanceiraClient analiseFinanceiraClient;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        Proposta proposta = propostaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe uma proposta com id %d", id)));

        PropostaDetalhesResponse propostaDetalhesResponse = new PropostaDetalhesResponse(proposta);
        return ResponseEntity.ok(propostaDetalhesResponse);
    }

    @Transactional
    @PostMapping
    public ResponseEntity criarProposta(UriComponentsBuilder uriComponentsBuilder,
                                        @RequestBody @Valid PropostaRequest propostaRequest) {

        boolean documentoJaCadastrado = propostaRepository.existsByDocumento(propostaRequest.getDocumento());

        if (documentoJaCadastrado) {
            return ResponseEntity.unprocessableEntity().build();
        }

        var proposta = propostaRequest.toModel();
        var novaProposta = propostaRepository.save(proposta);

        //TODO
        //      Estudar como refatorar
        //      Refatorar também para caso o link não responda (tirar setEstadoProposta do catch)

        try {
            var validacaoRequest = new AnaliseFinanceiraRequest(
                    String.valueOf(novaProposta.getId()),
                    novaProposta.getDocumento(),
                    novaProposta.getNome());

            analiseFinanceiraClient.avaliarProposta(validacaoRequest);
            novaProposta.setEstadoProposta(PropostaEstado.ELEGIVEL);
        } catch (FeignException e) {
            novaProposta.setEstadoProposta(PropostaEstado.NAO_ELEGIVEL);
        }

        if (novaProposta.eElegivel()) {
            publisher.publishEvent(new PropostaCriadaEvent(novaProposta));
        }

        URI enderecoRecurso = uriComponentsBuilder.path("/propostas/{id}").build(novaProposta.getId());
        return ResponseEntity.created(enderecoRecurso).build();
    }

}
