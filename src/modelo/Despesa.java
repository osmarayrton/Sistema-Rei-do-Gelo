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
@Table(name = "despesa")
@XmlRootElement/*c.cliStatus like :cliStatus order by c.cliNome ASC"*/
@NamedQueries({
    @NamedQuery(name = "Despesa.findAll", query = "SELECT d FROM Despesa d WHERE d.despStatus like :despStatus order by d.despDescricao ASC ")
    , @NamedQuery(name = "Despesa.findByDespCoddesp", query = "SELECT d FROM Despesa d WHERE d.despCoddesp = :despCoddesp")
    , @NamedQuery(name = "Despesa.findByDespValor", query = "SELECT d FROM Despesa d WHERE d.despValor = :despValor")
    , @NamedQuery(name = "Despesa.findByDespDataVencimento", query = "SELECT d FROM Despesa d WHERE d.despDataVencimento = :despDataVencimento")
    , @NamedQuery(name = "Despesa.findByDespDataPagamento", query = "SELECT d FROM Despesa d WHERE d.despDataPagamento = :despDataPagamento")
    , @NamedQuery(name = "Despesa.findByDespVlPago", query = "SELECT d FROM Despesa d WHERE d.despVlPago = :despVlPago")
    , @NamedQuery(name = "Despesa.findByCaixa", query = "SELECT d FROM Despesa d WHERE d.caixaCxCodcaixa = :caixa")
    , @NamedQuery(name = "Despesa.findByDespDescricao", query = "SELECT d FROM Despesa d WHERE d.despDescricao = :despDescricao")})
public class Despesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "desp_coddesp")
    private Integer despCoddesp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desp_valor")
    private Float despValor;
    @Column(name = "desp_data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date despDataVencimento;
    @Column(name = "desp_data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date despDataPagamento;
    @Column(name = "desp_vl_pago")
    private Float despVlPago;
    @Basic(optional = false)
    @Column(name = "desp_descricao")
    private String despDescricao;
    @Column(name="desp_status")
    private String despStatus;
    @Column(name="desp_statusPagamento")
    private String despStatusPagamento;
    @JoinColumn(name = "tipodespesa_tpdesp_cod", referencedColumnName = "tpdesp_cod")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoDespesa tipodespesaTpdespCod;
    @JoinColumn(name = "usuario_us_codusuario", referencedColumnName = "us_codusuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioUsCodusuario;
    @JoinColumn(name = "caixa_cx_codcaixa", referencedColumnName = "cx_codcaixa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caixa caixaCxCodcaixa;

    public Despesa() {
    }

    public Despesa(Integer despCoddesp) {
        this.despCoddesp = despCoddesp;
    }

    public Despesa(Integer despCoddesp, String despDescricao) {
        this.despCoddesp = despCoddesp;
        this.despDescricao = despDescricao;
    }

    public Integer getDespCoddesp() {
        return despCoddesp;
    }

    public void setDespCoddesp(Integer despCoddesp) {
        this.despCoddesp = despCoddesp;
    }

    public Float getDespValor() {
        return despValor;
    }

    public void setDespValor(Float despValor) {
        this.despValor = despValor;
    }

    public Date getDespDataVencimento() {
        return despDataVencimento;
    }

    public void setDespDataVencimento(Date despDataVencimento) {
        this.despDataVencimento = despDataVencimento;
    }

    public Date getDespDataPagamento() {
        return despDataPagamento;
    }

    public void setDespDataPagamento(Date despDataPagamento) {
        this.despDataPagamento = despDataPagamento;
    }

    public Float getDespVlPago() {
        return despVlPago;
    }

    public void setDespVlPago(Float despVlPago) {
        this.despVlPago = despVlPago;
    }

    public String getDespDescricao() {
        return despDescricao;
    }

    public void setDespDescricao(String despDescricao) {
        this.despDescricao = despDescricao;
    }

    public TipoDespesa getTipodespesaTpdespCod() {
        return tipodespesaTpdespCod;
    }

    public void setTipodespesaTpdespCod(TipoDespesa tipodespesaTpdespCod) {
        this.tipodespesaTpdespCod = tipodespesaTpdespCod;
    }

    public Usuario getUsuarioUsCodusuario() {
        return usuarioUsCodusuario;
    }

    public void setUsuarioUsCodusuario(Usuario usuarioUsCodusuario) {
        this.usuarioUsCodusuario = usuarioUsCodusuario;
    }

    public String getDespStatus() {
        return despStatus;
    }

    public void setDespStatus(String despStatus) {
        this.despStatus = despStatus;
    }

    public String getDespStatusPagamento() {
        return despStatusPagamento;
    }

    public void setDespStatusPagamento(String despStatusPagamento) {
        this.despStatusPagamento = despStatusPagamento;
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
        hash += (despCoddesp != null ? despCoddesp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despesa)) {
            return false;
        }
        Despesa other = (Despesa) object;
        if ((this.despCoddesp == null && other.despCoddesp != null) || (this.despCoddesp != null && !this.despCoddesp.equals(other.despCoddesp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Despesa[ despCoddesp=" + despCoddesp + " ]";
    }
    
}
