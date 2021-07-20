package br.com.zupacademy.nicolecatarina.proposta.proposta;

import br.com.zupacademy.nicolecatarina.proposta.cartao.Cartao;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private PropostaEstado propostaEstado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id_proposta")
    private Cartao cartao;

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Deprecated
    public Proposta(){
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void setEstadoProposta(PropostaEstado propostaEstado) {
        this.propostaEstado = propostaEstado;
    }

    public void vincularCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public boolean eElegivel() {
        return this.propostaEstado.equals(PropostaEstado.ELEGIVEL);
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public PropostaEstado getPropostaEstado() {
        return propostaEstado;
    }
}
