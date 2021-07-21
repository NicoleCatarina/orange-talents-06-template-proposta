package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;

import br.com.zupacademy.nicolecatarina.proposta.cartao.Bloqueio;

public class BloqueioGatewayResponse {

    private Long id;
    private Long idCartao;

    public Bloqueio toModel() {
        return new Bloqueio();
    }

}
