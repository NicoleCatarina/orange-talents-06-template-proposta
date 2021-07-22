package br.com.zupacademy.nicolecatarina.proposta.cartao.aviso;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destino;
    private LocalDate dataTermino;

    @CreationTimestamp
    private LocalDate instanteAviso;

    private String ipRequisicaoDeBloqueio;
    private String userAgent;

    private Long idCartao;

    public AvisoViagem(String destino, LocalDate dataTermino, String ipRequisicaoDeBloqueio, String userAgent) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.ipRequisicaoDeBloqueio = ipRequisicaoDeBloqueio;
        this.userAgent = userAgent;
    }

    @Deprecated
    public AvisoViagem() {
    }

}
