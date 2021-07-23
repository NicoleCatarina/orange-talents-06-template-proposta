package br.com.zupacademy.nicolecatarina.proposta.cartao;

import br.com.zupacademy.nicolecatarina.proposta.cartao.aviso.AvisoViagem;
import br.com.zupacademy.nicolecatarina.proposta.cartao.carteira.Carteira;
import br.com.zupacademy.nicolecatarina.proposta.cartao.carteira.ProvedorCarteira;
import br.com.zupacademy.nicolecatarina.proposta.cartao.parcela.Parcela;
import br.com.zupacademy.nicolecatarina.proposta.cartao.vencimento.Vencimento;
import br.com.zupacademy.nicolecatarina.proposta.proposta.Proposta;

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
    @OneToOne
    private Proposta proposta;
    @Enumerated(EnumType.STRING)
    private EstadoCartao estadoCartao = EstadoCartao.DESBLOQUEADO;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCartao")
    private List<Bloqueio> bloqueios;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCartao")
    private List<Carteira> carteiras;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCartao")
    private List<Parcela> parcelas;
    private List<AvisoViagem> avisosViagem;

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
                  Proposta proposta, Vencimento vencimento) {
        this.numero = numero;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.proposta = proposta;
        this.vencimento = Collections.singletonList(vencimento);
    }

    @Deprecated
    public Cartao() {
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void bloquear(Bloqueio bloqueio) {
        this.bloqueios.add(bloqueio);
    }

    public boolean estaBloqueado() {
        return this.estadoCartao.equals(EstadoCartao.BLOQUEADO);
    }

    public void associarAvisoDeViagem(AvisoViagem avisoViagem) {
        this.avisosViagem.add(avisoViagem);
    }

    public String getEmailDoDono() {
        return this.proposta.getEmail();
    }

    public void associarCarteira(Carteira carteira) {
        this.carteiras.add(carteira);
    }

    public boolean jaEhAssociadoACarteira(ProvedorCarteira provedor) {
        return this.carteiras.stream()
                .anyMatch(carteira -> carteira.getProvedor().equals(provedor));
    }
}
