/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author osmar
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u WHERE u.usStatus like :usStatus order by u.usNome ASC")
    , @NamedQuery(name = "Usuario.findByUsCodus", query = "SELECT u FROM Usuario u WHERE u.usCodusuario = :usCodus")
    , @NamedQuery(name = "Usuario.findByUsNome", query = "SELECT u FROM Usuario u WHERE u.usNome like :usNome AND u.usStatus = :usStatus order by u.usNome ASC")
    , @NamedQuery(name = "Usuario.findByUsTelefone", query = "SELECT u FROM Usuario u WHERE u.usTelefone = :usTelefone")
    , @NamedQuery(name = "Usuario.findByUsEmail", query = "SELECT u FROM Usuario u WHERE u.usEmail = :usEmail")
    , @NamedQuery(name = "Usuario.findByUsUsuario", query = "SELECT u FROM Usuario u WHERE u.usUsuario = :usUsuario")
    , @NamedQuery(name = "Usuario.findByUsSenha", query = "SELECT u FROM Usuario u WHERE u.usSenha = :usSenha")
    , @NamedQuery(name = "Usuario.conta", query = "SELECT count(u.usNome) FROM Usuario u where u.usNome is not null ")
    , @NamedQuery(name = "Usuario.acessar", query = "SELECT u FROM Usuario u WHERE u.usEmail = :email AND u.usSenha = :senha")
    , @NamedQuery(name = "Usuario.achar", query = "SELECT u FROM Usuario u WHERE u.usSenha = :senha AND u.usEmail = :email")
    , @NamedQuery(name = "Usuario.findByCliStatus", query = "SELECT u FROM Usuario u WHERE u.usStatus = :usStatus order by u.usNome ASC")})

public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioUsCodusuario", fetch = FetchType.LAZY)
    private List<Venda> vendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioUsCodusuario", fetch = FetchType.LAZY)
    private List<Caixa> caixaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioUsCodusuario", fetch = FetchType.LAZY)
    private List<Despesa> despesaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "us_codusuario")
    private Integer usCodusuario;
    @Column(name = "us_nome")
    private String usNome;
    @Column(name = "us_telefone")
    private String usTelefone;
    @Column(unique=true, name = "us_email")
    private String usEmail;
    @Column(unique=true, name = "us_usuario")
    private String usUsuario;
    @Column(name = "us_senha")
    private String usSenha;
    @Column(name = "us_cargo")
    private String usCargo;
    @Column(name ="us_status")
    private String usStatus;

    public Usuario() {
    }

    public Usuario(Integer usCodusuario) {
        this.usCodusuario = usCodusuario;
    }

    public Integer getUsCodusuario() {
        return usCodusuario;
    }

    public void setUsCodusuario(Integer usCodusuario) {
        this.usCodusuario = usCodusuario;
    }

    public String getUsNome() {
        return usNome;
    }

    public void setUsNome(String usNome) {
        this.usNome = usNome;
    }

    public String getUsTelefone() {
        return usTelefone;
    }

    public void setUsTelefone(String usTelefone) {
        this.usTelefone = usTelefone;
    }

    public String getUsEmail() {
        return usEmail;
    }

    public void setUsEmail(String usEmail) {
        this.usEmail = usEmail;
    }

    public String getUsUsuario() {
        return usUsuario;
    }

    public void setUsUsuario(String usUsuario) {
        this.usUsuario = usUsuario;
    }

    public String getUsSenha() {
        return usSenha;
    }

    public void setUsSenha(String usSenha) {
        this.usSenha = usSenha;
    }

    public String getUsCargo() {
        return usCargo;
    }

    public void setUsCargo(String usCargo) {
        this.usCargo = usCargo;
    }

    public String getUsStatus() {
        return usStatus;
    }

    public void setUsStatus(String usStatus) {
        this.usStatus = usStatus;
    }
    
    
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usCodusuario != null ? usCodusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usCodusuario == null && other.usCodusuario != null) || (this.usCodusuario != null && !this.usCodusuario.equals(other.usCodusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " "   + usNome;
    }

    

    @XmlTransient
    public List<Despesa> getDespesaList() {
        return despesaList;
    }

    public void setDespesaList(List<Despesa> despesaList) {
        this.despesaList = despesaList;
    }

    @XmlTransient
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

    @XmlTransient
    public List<Caixa> getCaixaList() {
        return caixaList;
    }

    public void setCaixaList(List<Caixa> caixaList) {
        this.caixaList = caixaList;
    }

    public Object getUsSenha(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
