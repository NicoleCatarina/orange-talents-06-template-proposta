package br.com.zupacademy.nicolecatarina.proposta.cartao;

public class BloqueioGatewayResponse {

    private Long id;
    private Long idCartao;

    public Bloqueio toModel() {
        return new Bloqueio();
    }

}
