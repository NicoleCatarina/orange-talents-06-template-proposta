package br.com.zupacademy.nicolecatarina.proposta.cartao.biometria;

import br.com.zupacademy.nicolecatarina.proposta.cartao.Cartao;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] fingerPrint;
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;

    public Biometria(String fingerPrint, Cartao cartao) {
        this.fingerPrint = Base64.getEncoder().encode(fingerPrint.getBytes());
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

}
