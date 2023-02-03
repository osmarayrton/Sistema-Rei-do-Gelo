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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")
    , @NamedQuery(name = "Pagamento.findByCodpagamento", query = "SELECT p FROM Pagamento p WHERE p.codpagamento = :codpagamento")
    , @NamedQuery(name = "Pagamento.findByPagValorpago", query = "SELECT p FROM Pagamento p WHERE p.pagValorpago = :pagValorpago")
    , @NamedQuery(name = "Pagamento.findByCaixa", query = "SELECT p FROM Pagamento p WHERE p.caixaCxCodcaixa = :caixa")})
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codpagamento")
    private Integer codpagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pag_valorpago")
    private Float pagValorpago;
    @Column(name = "pag_data")
    @Temporal(TemporalType.DATE)
    private Date pagData;
    @Column(name="pag_meiopagamento")
    private String pagMeioPagamento;
    @JoinColumn(name = "caixa_cx_codcaixa", referencedColumnName = "cx_codcaixa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caixa caixaCxCodcaixa;
    @JoinColumn(name = "venda_vnd_codvend", referencedColumnName = "vnd_codvend")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Venda venda;
    

    public Pagamento() {
    }
    
    public Pagamento(Integer codpagamento) {
        this.codpagamento = codpagamento;
    }

    public Integer getCodpagamento() {
        return codpagamento;
    }

    public void setCodpagamento(Integer codpagamento) {
        this.codpagamento = codpagamento;
    }

    public Float getPagValorpago() {
        return pagValorpago;
    }

    public void setPagValorpago(Float pagValorpago) {
        this.pagValorpago = pagValorpago;
    }

    public Date getPagData() {
        return pagData;
    }

    public void setPagData(Date pagData) {
        this.pagData = pagData;
    }

    public Caixa getCaixaCxCodcaixa() {
        return caixaCxCodcaixa;
    }

    public void setCaixaCxCodcaixa(Caixa caixaCxCodcaixa) {
        this.caixaCxCodcaixa = caixaCxCodcaixa;
    }

    public Venda getVendaVndCodvend() {
        return venda;
    }

    public void setVendaVndCodvend(Venda venda) {
        this.venda = venda;
    }

    public String getPagMeioPagamento() {
        return pagMeioPagamento;
    }

    public void setPagMeioPagamento(String pagMeioPagamento) {
        this.pagMeioPagamento = pagMeioPagamento;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpagamento != null ? codpagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.codpagamento == null && other.codpagamento != null) || (this.codpagamento != null && !this.codpagamento.equals(other.codpagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Pagamento[ codpagamento=" + codpagamento + " ]";
    }

   
}
