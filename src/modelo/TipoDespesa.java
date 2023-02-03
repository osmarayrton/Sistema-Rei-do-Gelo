/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author osmar
 */
@Entity
@Table(name = "tipodespesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDespesa.findAll", query = "SELECT t FROM TipoDespesa t WHERE t.tpdespStatus like :tpdespStatus order by t.tpdespDescricao ASC")
    , @NamedQuery(name = "TipoDespesa.findByTpdespCod", query = "SELECT t FROM TipoDespesa t WHERE t.tpdespCod = :tpdespCod")
    , @NamedQuery(name = "TipoDespesa.findByTpdespDescricao", query = "SELECT t FROM TipoDespesa t WHERE t.tpdespDescricao = :tpdespDescricao")})
public class TipoDespesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tpdesp_cod")
    private Integer tpdespCod;
    @Column(unique=true, name = "tpdesp_descricao")
    private String tpdespDescricao;
    @Column(name = "tpdesp_status")
    private String tpdespStatus;

    public TipoDespesa() {
    }

    public TipoDespesa(Integer tpdespCod) {
        this.tpdespCod = tpdespCod;
    }

    public Integer getTpdespCod() {
        return tpdespCod;
    }

    public void setTpdespCod(Integer tpdespCod) {
        this.tpdespCod = tpdespCod;
    }

    public String getTpdespDescricao() {
        return tpdespDescricao;
    }

    public void setTpdespDescricao(String tpdespDescricao) {
        this.tpdespDescricao = tpdespDescricao;
    }

    public String getTpdespStatus() {
        return tpdespStatus;
    }

    public void setTpdespStatus(String tpdespStatus) {
        this.tpdespStatus = tpdespStatus;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpdespCod != null ? tpdespCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDespesa)) {
            return false;
        }
        TipoDespesa other = (TipoDespesa) object;
        if ((this.tpdespCod == null && other.tpdespCod != null) || (this.tpdespCod != null && !this.tpdespCod.equals(other.tpdespCod))) {
            return false;
        }
        if (this.tpdespDescricao.equals(other.getTpdespDescricao())) {
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return " " + tpdespDescricao + " ";
    }

}
