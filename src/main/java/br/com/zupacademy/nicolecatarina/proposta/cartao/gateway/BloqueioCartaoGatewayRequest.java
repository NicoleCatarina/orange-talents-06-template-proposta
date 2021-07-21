package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;

public class BloqueioCartaoGatewayRequest {

    private String sistemaResponsavel;

    public BloqueioCartaoGatewayRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

}
