package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;

public class BloqueioCartaoGatewayReponse {

    private BloqueioCartaoResultado resultado;

    public BloqueioCartaoResultado getResultado() {
        return resultado;
    }

    /**
     * setter ao invés de constructor porque o jackson não conseguiu serializar a partir do construtor
     * @param resultado
     */
    public void setResultado(BloqueioCartaoResultado resultado) {
        this.resultado = resultado;
    }

}
