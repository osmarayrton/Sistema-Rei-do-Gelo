/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author osmar
 */
@Entity
@Table(name = "movimentacoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimentacoes.findAll", query = "SELECT m FROM Movimentacoes m")
    , @NamedQuery(name = "Movimentacoes.findByCodmovimentacao", query = "SELECT m FROM Movimentacoes m WHERE m.codmovimentacao = :codmovimentacao")
    , @NamedQuery(name = "Movimentacoes.findByMovOperacao", query = "SELECT m FROM Movimentacoes m WHERE m.movOperacao = :movOperacao")
    , @NamedQuery(name = "Movimentacoes.findByMovValor", query = "SELECT m FROM Movimentacoes m WHERE m.movValor = :movValor")
    , @NamedQuery(name = "Movimentacoes.findByMovData", query = "SELECT m FROM Movimentacoes m WHERE m.movData = :movData")
    , @NamedQuery(name = "Movimentacoes.findByMovHora", query = "SELECT m FROM Movimentacoes m WHERE m.movHora = :movHora")
    , @NamedQuery(name = "Movimentacoes.findByMovMotivo", query = "SELECT m FROM Movimentacoes m WHERE m.movMotivo = :movMotivo")})
public class Movimentacoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codmovimentacao")
    private Integer codmovimentacao;
    @Column(name = "mov_operacao")
    private String movOperacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mov_valor")
    private Float movValor;
    @Column(name = "mov_data")
    @Temporal(TemporalType.DATE)
    private Date movData;
    @Column(name = "mov_hora")
    @Temporal(TemporalType.TIME)
    private Date movHora;
    @Column(name = "mov_motivo")
    private String movMotivo;
    @JoinColumn(name = "caixa_cx_codcaixa", referencedColumnName = "cx_codcaixa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caixa caixaCxCodcaixa;

    public Movimentacoes() {
    }

    public Movimentacoes(Integer codmovimentacao) {
        this.codmovimentacao = codmovimentacao;
    }

    public Integer getCodmovimentacao() {
        return codmovimentacao;
    }

    public void setCodmovimentacao(Integer codmovimentacao) {
        this.codmovimentacao = codmovimentacao;
    }

    public String getMovOperacao() {
        return movOperacao;
    }

    public void setMovOperacao(String movOperacao) {
        this.movOperacao = movOperacao;
    }

    public Float getMovValor() {
        return movValor;
    }

    public void setMovValor(Float movValor) {
        this.movValor = movValor;
    }

    public Date getMovData() {
        return movData;
    }

    public void setMovData(Date movData) {
        this.movData = movData;
    }

    public Date getMovHora() {
        return movHora;
    }

    public void setMovHora(Date movHora) {
        this.movHora = movHora;
    }

    public String getMovMotivo() {
        return movMotivo;
    }

    public void setMovMotivo(String movMotivo) {
        this.movMotivo = movMotivo;
    }

    public Caixa getCaixaCxCodcaixa() {
        return caixaCxCodcaixa;
    }

    public void setCaixaCxCodcaixa(Caixa caixaCxCodcaixa) {
        this.caixaCxCodcaixa = caixaCxCodcaixa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmovimentacao != null ? codmovimentacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimentacoes)) {
            return false;
        }
        Movimentacoes other = (Movimentacoes) object;
        if ((this.codmovimentacao == null && other.codmovimentacao != null) || (this.codmovimentacao != null && !this.codmovimentacao.equals(other.codmovimentacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Movimentacoes[ codmovimentacao=" + codmovimentacao + " ]";
    }
    
}
