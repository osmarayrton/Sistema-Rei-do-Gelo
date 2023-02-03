/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author osmar
 */
@Embeddable
public class ItemVendaPK implements Serializable {


    private Integer venda;

    private Integer produto;
    

    

    public ItemVendaPK() {
    }

    public ItemVendaPK(int vendaVndCodvend, int produtoProdCodprod) {
        this.venda = vendaVndCodvend;
        this.produto = produtoProdCodprod;
    }

    public int getVenda() {
        return venda;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) venda;
        hash += (int) produto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemVendaPK)) {
            return false;
        }
        ItemVendaPK other = (ItemVendaPK) object;
        if (this.venda != other.venda) {
            return false;
        }
        if (this.produto != other.produto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ItemvendaPK[ vendaVndCodvend=" + venda + ", produtoProdCodprod=" + produto + " ]";
    }
    
}
