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

/**
 *
 * @author Mauricio
 * c.cliStatus like :cliStatus order by c.cliNome ASC
 * 
 * SELECT p FROM Produto p WHERE p.prodStatus like :prodStatus order by p.prodDescricao ASC
 */
@Entity
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p WHERE p.prodStatus like :prodStatus order by p.prodDescricao ASC ")
    , @NamedQuery(name = "Produto.findByProdCodprod", query = "SELECT p FROM Produto p WHERE p.prodCodprod = :prodCodprod")
    , @NamedQuery(name = "Produto.findByProdDescricao", query = "SELECT p FROM Produto p WHERE p.prodDescricao like :prodDescricao")
    , @NamedQuery(name = "Produto.findByProdDescricaoIdentica", query = "SELECT p FROM Produto p WHERE p.prodDescricao = :prodDescricao")
    , @NamedQuery(name = "Produto.findByProdPreco", query = "SELECT p FROM Produto p WHERE p.prodPreco = :prodPreco")
    , @NamedQuery(name = "Produto.findByProdStatus", query = "SELECT p FROM Produto p WHERE p.prodStatus = :prodStatus order by p.prodDescricao")})

public class Produto implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_codprod")
    private Integer prodCodprod;
    @Column( name = "prod_descricao")
    private String prodDescricao;
    @Column(name = "prod_preco")
    private Float prodPreco;
    @Column(name = "prod_status")
    private String prodStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto", fetch = FetchType.LAZY)
    private List<ItemVenda> itemvendaList;

    public Produto() {
    }

    public Produto(Integer prodCodprod) {
        this.prodCodprod = prodCodprod;
    }

    public Integer getProdCodprod() {
        return prodCodprod;
    }

    public void setProdCodprod(Integer prodCodprod) {
        this.prodCodprod = prodCodprod;
    }

    public String getProdDescricao() {
        return prodDescricao;
    }

    public void setProdDescricao(String prodDescricao) {
        this.prodDescricao = prodDescricao;
    }

    public Float getProdPreco() {
        return prodPreco;
    }

    public void setProdPreco(Float prodPreco) {
        this.prodPreco = prodPreco;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }
    
    public String getProdStatus() {
        return prodStatus;
    }
    
    public List<ItemVenda> getItemvendaList() {
        return itemvendaList;
    }

    public void setItemvendaList(List<ItemVenda> itemvendaList) {
        this.itemvendaList = itemvendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodCodprod != null ? prodCodprod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.prodCodprod == null && other.prodCodprod != null) || (this.prodCodprod != null && !this.prodCodprod.equals(other.prodCodprod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " " + prodDescricao + " ";
    }

    

}
