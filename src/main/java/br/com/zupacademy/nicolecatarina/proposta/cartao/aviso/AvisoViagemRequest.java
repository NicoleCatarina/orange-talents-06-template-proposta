package br.com.zupacademy.nicolecatarina.proposta.cartao.aviso;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataTermino;

    public AvisoViagemRequest() {
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public AvisoViagem toModel(String ipClienteDaRequisicao, String userAgent) {
        return new AvisoViagem(this.getDestino(), this.getDataTermino(), ipClienteDaRequisicao, userAgent);
    }

}
