package br.com.zupacademy.nicolecatarina.proposta.cartao;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime instanteBloqueio;
    private String ipRequisicaoDeBloqueio;
    private String userAgent;
    private Long idCartao;

    public Bloqueio(String ipRequisicaoDeBloqueio, String userAgent) {
        this.ipRequisicaoDeBloqueio = ipRequisicaoDeBloqueio;
        this.userAgent = userAgent;
    }

    @Deprecated
    public Bloqueio() {
    }
}
