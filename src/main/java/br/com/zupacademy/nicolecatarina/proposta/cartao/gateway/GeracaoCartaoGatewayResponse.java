package br.com.zupacademy.nicolecatarina.proposta.cartao.gateway;

import br.com.zupacademy.nicolecatarina.proposta.cartao.Cartao;
import br.com.zupacademy.nicolecatarina.proposta.cartao.gateway.*;

import java.time.LocalDateTime;
import java.util.List;

public class GeracaoCartaoGatewayResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private String idProposta;
    private Long limite;
    private List<BloqueioGatewayResponse> bloqueios;
    private List<AvisoGatewayResponse> avisos;
    private List<CarteiraGatewayResponse> carteiras;
    private List<ParcelaGatewayResponse> parcelas;
    private VencimentoGatewayResponse vencimento;
    private String renegociacao;

    public GeracaoCartaoGatewayResponse(String id, LocalDateTime emitidoEm, String titular, String idProposta,
                                        Long limite, List<BloqueioGatewayResponse> bloqueios,
                                        List<AvisoGatewayResponse> avisos, List<CarteiraGatewayResponse> carteiras,
                                        List<ParcelaGatewayResponse> parcelas, VencimentoGatewayResponse vencimento,
                                        String renegociacao) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.idProposta = idProposta;
        this.limite = limite;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.vencimento = vencimento;
        this.renegociacao = renegociacao;
    }

    public Cartao toModel() {
        return new Cartao(this.id, this.emitidoEm, this.titular, this.limite, this.renegociacao,
                Long.parseLong(this.idProposta), this.vencimento.toModel());
    }

}
