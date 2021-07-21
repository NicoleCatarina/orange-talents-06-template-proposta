package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;

public class GeracaoCartaoGatewayRequest {

    private String idProposta;
    private String documento;
    private String nome;

    public GeracaoCartaoGatewayRequest(String idProposta, String documento, String nome) {
        this.idProposta = idProposta;
        this.documento = documento;
        this.nome = nome;
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

}
