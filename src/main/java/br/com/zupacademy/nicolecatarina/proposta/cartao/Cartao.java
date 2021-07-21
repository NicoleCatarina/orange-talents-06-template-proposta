package br.com.zupacademy.nicolecatarina.proposta.cartao;

import br.com.zupacademy.nicolecatarina.proposta.cartao.aviso.Aviso;
import br.com.zupacademy.nicolecatarina.proposta.cartao.carteira.Carteira;
import br.com.zupacademy.nicolecatarina.proposta.cartao.parcela.Parcela;
import br.com.zupacademy.nicolecatarina.proposta.cartao.vencimento.Vencimento;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private LocalDateTime emitidoEm;
    private String titular;
    private Long limite;
    private String renegociacao;
    private Long idProposta;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCartao")
    private List<Bloqueio> bloqueios;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCartao")
    private List<Aviso> avisos;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCartao")
    private List<Carteira> carteiras;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCartao")
    private List<Parcela> parcelas;

    //TODO
    //fazer relacionamento para salvar o id do cartao no vencimento, onetomany foi workaround
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCartao")
    private List<Vencimento> vencimento;

//    public Cartao(String numero, LocalDateTime emitidoEm, String titular, Long limite, String renegociacao,
//                  Long idProposta, List<Bloqueio> bloqueios, List<Aviso> avisos, List<Carteira> carteiras,
//                  List<Parcela> parcelas, Vencimento vencimento) {
//        this.numero = numero;
//        this.emitidoEm = emitidoEm;
//        this.titular = titular;
//        this.limite = limite;
//        this.renegociacao = renegociacao;
//        this.idProposta = idProposta;
//        this.bloqueios = bloqueios;
//        this.avisos = avisos;
//        this.carteiras = carteiras;
//        this.parcelas = parcelas;
//        this.vencimento = vencimento;
//    }

    //CONSTRUTOR TEMPORARIO
    //TODO
    //      Refatorar para nao receber idProposta e usar o cascade da classe Proposta
    public Cartao(String numero, LocalDateTime emitidoEm, String titular, Long limite, String renegociacao,
                  Long idProposta, Vencimento vencimento) {
        this.numero = numero;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.idProposta = idProposta;
        this.vencimento = Collections.singletonList(vencimento);
    }

    @Deprecated
    public Cartao() {
    }

    public Long getId() {
        return id;
    }

}
