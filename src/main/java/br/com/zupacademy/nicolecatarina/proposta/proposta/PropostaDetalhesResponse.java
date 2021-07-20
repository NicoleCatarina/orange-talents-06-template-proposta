package br.com.zupacademy.nicolecatarina.proposta.proposta;

import java.math.BigDecimal;

public class PropostaDetalhesResponse {

    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private String estadoDaProposta;

    public PropostaDetalhesResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.estadoDaProposta = proposta.getPropostaEstado().getNomeRepresentacao();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getEstadoDaProposta() {
        return estadoDaProposta;
    }

}
