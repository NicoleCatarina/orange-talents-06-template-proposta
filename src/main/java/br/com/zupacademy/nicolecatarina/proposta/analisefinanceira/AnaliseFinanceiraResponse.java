package br.com.zupacademy.nicolecatarina.proposta.analisefinanceira;

public class AnaliseFinanceiraResponse {

    private String idProposta;
    private String documento;
    private String nome;
    private AnaliseFinanceiraResultado resultadoSolicitacao;

    public AnaliseFinanceiraResponse(String idProposta, String documento, String nome, AnaliseFinanceiraResultado resultadoSolicitacao) {
        this.idProposta = idProposta;
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public AnaliseFinanceiraResultado getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

}
