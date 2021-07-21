package br.com.zupacademy.nicolecatarina.proposta.cartao.biometria;

import br.com.zupacademy.nicolecatarina.proposta.cartao.Cartao;
import br.com.zupacademy.nicolecatarina.proposta.cartao.biometria.Biometria;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    private String fingerPrint;

    public BiometriaRequest(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(fingerPrint, cartao);
    }

}
