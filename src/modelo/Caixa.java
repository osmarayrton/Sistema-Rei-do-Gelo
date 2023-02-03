/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author osmar
 */
@Entity
@Table(name = "caixa")
@XmlRootElement
@NamedQueries({
     @NamedQuery(name = "Caixa.buscaAberto", query = "SELECT c FROM Caixa c where c.cxHoraFechamento is null")
    ,
    @NamedQuery(name = "Caixa.buscarRecebimento", query = "SELECT c FROM Caixa c where c.cxCodcaixa =:codigo")
    ,
    @NamedQuery(name = "Caixa.findAll", query = "SELECT c FROM Caixa c")
    , @NamedQuery(name = "Caixa.findByCxCodcaixa", query = "SELECT c FROM Caixa c WHERE c.cxCodcaixa = :cxCodcaixa")
    , @NamedQuery(name = "Caixa.findByCxVlAbertura", query = "SELECT c FROM Caixa c WHERE c.cxVlAbertura = :cxVlAbertura")
    , @NamedQuery(name = "Caixa.findByCxDataAbertura", query = "SELECT c FROM Caixa c WHERE c.cxDataAbertura = :cxDataAbertura")
    , @NamedQuery(name = "Caixa.findByCxHoraAbertura", query = "SELECT c FROM Caixa c WHERE c.cxHoraAbertura = :cxHoraAbertura")
    , @NamedQuery(name = "Caixa.findByCxVlFechamento", query = "SELECT c FROM Caixa c WHERE c.cxVlFechamento = :cxVlFechamento")
    , @NamedQuery(name = "Caixa.findByCxDataFechamento", query = "SELECT c FROM Caixa c WHERE c.cxDataFechamento = :cxDataFechamento")
    , @NamedQuery(name = "Caixa.findByCxHoraFechamento", query = "SELECT c FROM Caixa c WHERE c.cxHoraFechamento = :cxHoraFechamento")})
public class Caixa implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cx_codcaixa")
    private Integer cxCodcaixa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cx_vl_abertura")
    private Float cxVlAbertura;
    @Column(name = "cx_data_abertura")
    @Temporal(TemporalType.DATE)
    private Date cxDataAbertura;
    @Column(name = "cx_hora_abertura")
    
    private Timestamp cxHoraAbertura;
    @Column(name = "cx_vl_fechamento")
    private Float cxVlFechamento;
    @Column(name = "cx_data_fechamento")
    @Temporal(TemporalType.DATE)
    private Date cxDataFechamento;
    @Column(name = "cx_hora_fechamento")
    
    private Timestamp cxHoraFechamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caixaCxCodcaixa", fetch = FetchType.LAZY)
    private List<Movimentacoes> movimentacoesList;
    @JoinColumn(name = "usuario_us_codusuario", referencedColumnName = "us_codusuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioUsCodusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caixaCxCodcaixa", fetch = FetchType.LAZY)
    private List<Pagamento> pagamentoList;

    public Caixa() {
    }

    public Caixa(Integer cxCodcaixa) {
        this.cxCodcaixa = cxCodcaixa;
    }

    public Integer getCxCodcaixa() {
        return cxCodcaixa;
    }

    public void setCxCodcaixa(Integer cxCodcaixa) {
        this.cxCodcaixa = cxCodcaixa;
    }

    public Float getCxVlAbertura() {
        return cxVlAbertura;
    }

    public void setCxVlAbertura(Float cxVlAbertura) {
        this.cxVlAbertura = cxVlAbertura;
    }

    public Date getCxDataAbertura() {
        return cxDataAbertura;
    }

    public void setCxDataAbertura(Date cxDataAbertura) {
        this.cxDataAbertura = cxDataAbertura;
    }

    public Timestamp getCxHoraAbertura() {
        return cxHoraAbertura;
    }

    public void setCxHoraAbertura(Timestamp cxHoraAbertura) {
        this.cxHoraAbertura = cxHoraAbertura;
    }
    
    public Timestamp getCxHoraFechamento() {
        return cxHoraAbertura;
    }

    public void setCxHoraFechamento(Timestamp cxHoraFechamento) {
        this.cxHoraFechamento = cxHoraFechamento;
    }

    public Float getCxVlFechamento() {
        return cxVlFechamento;
    }

    public void setCxVlFechamento(Float cxVlFechamento) {
        this.cxVlFechamento = cxVlFechamento;
    }

    public Date getCxDataFechamento() {
        return cxDataFechamento;
    }

    public void setCxDataFechamento(Date cxDataFechamento) {
        this.cxDataFechamento = cxDataFechamento;
    }

    

    @XmlTransient
    public List<Movimentacoes> getMovimentacoesList() {
        return movimentacoesList;
    }

    public void setMovimentacoesList(List<Movimentacoes> movimentacoesList) {
        this.movimentacoesList = movimentacoesList;
    }

    public Usuario getUsuarioUsCodusuario() {
        return usuarioUsCodusuario;
    }

    public void setUsuarioUsCodusuario(Usuario usuarioUsCodusuario) {
        this.usuarioUsCodusuario = usuarioUsCodusuario;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cxCodcaixa != null ? cxCodcaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caixa)) {
            return false;
        }
        Caixa other = (Caixa) object;
        if ((this.cxCodcaixa == null && other.cxCodcaixa != null) || (this.cxCodcaixa != null && !this.cxCodcaixa.equals(other.cxCodcaixa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Caixa[ cxCodcaixa=" + cxCodcaixa + " ]";
    }
    
}
