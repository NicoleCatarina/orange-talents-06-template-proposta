package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;

import br.com.zupacademy.nicolecatarina.proposta.cartao.carteira.ProvedorCarteira;

public class CarteiraGatewayRequest {

    private String email;
    private ProvedorCarteira carteira;

    public CarteiraGatewayRequest(String email, ProvedorCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public ProvedorCarteira getCarteira() {
        return carteira;
    }

}
