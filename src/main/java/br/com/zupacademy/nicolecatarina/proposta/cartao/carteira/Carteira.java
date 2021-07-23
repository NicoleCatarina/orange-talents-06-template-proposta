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
    private ProvedorCarteira carteira;

    private Long idCartao;

    public Carteira(String identificador, String email, ProvedorCarteira carteira) {
        this.identificador = identificador;
        this.email = email;
        this.carteira = carteira;
    }

    public Carteira() {
    }

    public ProvedorCarteira getCarteira() {
        return carteira;
    }
}
