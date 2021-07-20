package br.com.zupacademy.nicolecatarina.proposta.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificadorVencimento;
    private int dia;
    private LocalDateTime dataCriacao;
    private Long idCartao;

    public Vencimento(String identificadorVencimento, int dia, LocalDateTime dataCriacao) {
        this.identificadorVencimento = identificadorVencimento;
        this.dia = dia;
        this.dataCriacao = dataCriacao;
    }

    @Deprecated
    public Vencimento() {
    }

}
