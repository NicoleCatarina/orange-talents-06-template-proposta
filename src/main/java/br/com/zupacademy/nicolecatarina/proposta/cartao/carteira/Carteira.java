package br.com.zupacademy.nicolecatarina.proposta.cartao.carteira;

import javax.persistence.*;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificador;
    private String email;
    @Enumerated(EnumType.STRING)
    private ProvedorCarteira provedor;
    private Long idCartao;

    public Carteira(String identificador, String email, ProvedorCarteira provedor) {
        this.identificador = identificador;
        this.email = email;
        this.provedor = provedor;
    }

    public Carteira() {
    }

    public ProvedorCarteira getProvedor() {
        return provedor;
    }
}
