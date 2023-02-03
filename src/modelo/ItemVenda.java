/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author osmar
 */
@Entity
@Table(name = "itemvenda")
@NamedQueries({
    @NamedQuery(name = "ItemVenda.findAll", query = "SELECT i FROM ItemVenda i")
    , @NamedQuery(name = "ItemVenda.findByItvQtd", query = "SELECT i FROM ItemVenda i WHERE i.itvQtd = :itvQtd")
    , @NamedQuery(name = "ItemVenda.findByItvValorUni", query = "SELECT i FROM ItemVenda i WHERE i.itvValorUni = :itvValorUni")
})

@IdClass(ItemVendaPK.class)
public class ItemVenda implements Serializable {
    
    
    @Column(name = "itv_qtd")
    private Integer itvQtd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "itv_valor_uni")
    private Float itvValorUni;

    @Id
    @JoinColumn(name = "Venda_vnd_codVend", referencedColumnName = "vnd_codvend")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Venda venda;
    
    @Id
    @JoinColumn(name = "Produto_prod_codProd", referencedColumnName = "prod_codprod")
    @ManyToOne( optional =false)
    private Produto produto;
    
    
    

    public ItemVenda() {
    }

  

    public Integer getItvQtd() {
        return itvQtd;
    }

    public void setItvQtd(Integer itvQtd) {
        this.itvQtd = itvQtd;
    }

    public Float getItvValorUni() {
        return itvValorUni;
    }

    public void setItvValorUni(Float itvValorUni) {
        this.itvValorUni = itvValorUni;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
   
}
