package br.com.zupacademy.nicolecatarina.proposta.cartao.carteira;

import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @NotNull
    private ProvedorCarteira provedorCarteira;

    public CarteiraRequest() {
    }

    public ProvedorCarteira getProvedorCarteira() {
        return provedorCarteira;
    }

}
