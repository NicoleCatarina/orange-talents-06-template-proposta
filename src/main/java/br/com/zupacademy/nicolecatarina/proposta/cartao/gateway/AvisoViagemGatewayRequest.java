package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;

import java.time.LocalDate;

public class AvisoViagemGatewayRequest {

    private String destino;
    private LocalDate validoAte;

    public AvisoViagemGatewayRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

}
