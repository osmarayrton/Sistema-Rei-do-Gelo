/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c WHERE c.cliStatus like :cliStatus order by c.cliNome ASC")
    , @NamedQuery(name = "Cliente.findByCliCodcli", query = "SELECT c FROM Cliente c WHERE c.cliCodcli = :cliCodcli")
    , @NamedQuery(name = "Cliente.findByCliTipo", query = "SELECT c FROM Cliente c WHERE c.cliTipo = :cliTipo")
    , @NamedQuery(name = "Cliente.findByCliNome", query = "SELECT c FROM Cliente c WHERE c.cliNome like :cliNome AND c.cliStatus = :cliStatus order by c.cliNome ASC")
    , @NamedQuery(name = "Cliente.findByCliCpfCnpj", query = "SELECT c FROM Cliente c WHERE c.cliCpfCnpj = :cliCpfCnpj order by c.cliNome ASC")
    , @NamedQuery(name = "Cliente.findByCliDataNasc", query = "SELECT c FROM Cliente c WHERE c.cliDataNasc = :cliDataNasc")
    , @NamedQuery(name = "Cliente.findByCliTelefone", query = "SELECT c FROM Cliente c WHERE c.cliTelefone = :cliTelefone")
    , @NamedQuery(name = "Cliente.findByCliLimite", query = "SELECT c FROM Cliente c WHERE c.cliLimite = :cliLimite")
    , @NamedQuery(name = "Cliente.findByCliEmail", query = "SELECT c FROM Cliente c WHERE c.cliEmail = :cliEmail")
    , @NamedQuery(name = "Cliente.findByCliLogradouro", query = "SELECT c FROM Cliente c WHERE c.cliLogradouro = :cliLogradouro")
    , @NamedQuery(name = "Cliente.findByCliNumero", query = "SELECT c FROM Cliente c WHERE c.cliNumero = :cliNumero")
    , @NamedQuery(name = "Cliente.findByCliBairro", query = "SELECT c FROM Cliente c WHERE c.cliBairro = :cliBairro")
    , @NamedQuery(name = "Cliente.findByCliCidade", query = "SELECT c FROM Cliente c WHERE c.cliCidade = :cliCidade")
    , @NamedQuery(name = "Cliente.findByCliEstado", query = "SELECT c FROM Cliente c WHERE c.cliEstado = :cliEstado")
    , @NamedQuery(name = "Cliente.findByCliCep", query = "SELECT c FROM Cliente c WHERE c.cliCep = :cliCep")
    , @NamedQuery(name = "Cliente.findByCliStatus", query = "SELECT c FROM Cliente c WHERE c.cliStatus like :cliStatus order by c.cliNome ASC")})
//    , @NamedQuery(name = "Cliente.findByNomeStatus", query = "SELECT c FROM Cliente c WHERE c.cliStatus = :A and c.cliNome like :nome") })

public class Cliente implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cli_limite")
    private Float cliLimite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteCliCodcli", fetch = FetchType.LAZY)
    private List<Venda> vendaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cli_codcli")
    private Integer cliCodcli;
    @Column(name = "cli_tipo")
    private String cliTipo;
    @Column(name = "cli_nome")
    private String cliNome;
    @Column(unique = true,name = "cli_cpf_cnpj")
    private String cliCpfCnpj;
    @Column(name = "cli_data_nasc",nullable = true )
    @Temporal(TemporalType.DATE)
    private Date cliDataNasc;
    @Column(name = "cli_telefone")
    private String cliTelefone;
    @Column(name = "cli_email")
    private String cliEmail;
    @Column(name = "cli_logradouro")
    private String cliLogradouro;
    @Column(name = "cli_numero")
    private String cliNumero;
    @Column(name = "cli_bairro")
    private String cliBairro;
    @Column(name = "cli_cidade")
    private String cliCidade;
    @Column(name = "cli_estado")
    private String cliEstado;
    @Column(name = "cli_cep")
    private String cliCep;
    @Column(name = "cli_status")
    private String cliStatus;

    public Cliente() {
    }

    public Cliente(Integer cliCodcli) {
        this.cliCodcli = cliCodcli;
    }

    public Integer getCliCodcli() {
        return cliCodcli;
    }

    public void setCliCodcli(Integer cliCodcli) {
        this.cliCodcli = cliCodcli;
    }

    public String getCliTipo() {
        return cliTipo;
    }

    public void setCliTipo(String cliTipo) {
        this.cliTipo = cliTipo;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliCpfCnpj() {
        return cliCpfCnpj;
    }

    public void setCliCpfCnpj(String cliCpfCnpj) {
        this.cliCpfCnpj = cliCpfCnpj;
    }

    public Date getCliDataNasc() {
        return cliDataNasc;
    }

    public void setCliDataNasc(Date cliDataNasc) {
        this.cliDataNasc = cliDataNasc;
    }

    public String getCliTelefone() {
        return cliTelefone;
    }

    public void setCliTelefone(String cliTelefone) {
        this.cliTelefone = cliTelefone;
    }

    public Float getCliLimite() {
        return cliLimite;
    }

    public void setCliLimite(Float cliLimite) {
        this.cliLimite = cliLimite;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public String getCliLogradouro() {
        return cliLogradouro;
    }

    public void setCliLogradouro(String cliLogradouro) {
        this.cliLogradouro = cliLogradouro;
    }

    public String getCliNumero() {
        return cliNumero;
    }

    public void setCliNumero(String cliNumero) {
        this.cliNumero = cliNumero;
    }

    public String getCliBairro() {
        return cliBairro;
    }

    public void setCliBairro(String cliBairro) {
        this.cliBairro = cliBairro;
    }

    public String getCliCidade() {
        return cliCidade;
    }

    public void setCliCidade(String cliCidade) {
        this.cliCidade = cliCidade;
    }

    public String getCliEstado() {
        return cliEstado;
    }

    public void setCliEstado(String cliEstado) {
        this.cliEstado = cliEstado;
    }

    public String getCliCep() {
        return cliCep;
    }

    public void setCliCep(String cliCep) {
        this.cliCep = cliCep;
    }

    public String getCliStatus() {
        return cliStatus;
    }

    public void setCliStatus(String cliStatus) {
        this.cliStatus = cliStatus;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliCodcli != null ? cliCodcli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cliCodcli == null && other.cliCodcli != null) || (this.cliCodcli != null && !this.cliCodcli.equals(other.cliCodcli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " " + cliNome + " ";
    }

    @XmlTransient
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

}
