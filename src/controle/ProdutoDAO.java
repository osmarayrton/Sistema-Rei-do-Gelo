/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import modelo.Produto;
import util.GerenciadorEntidades;

/**
 *
 * @author Mauricio
 */
public class ProdutoDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaReiDoGeloPU");
        return factory.createEntityManager();
    }

    public void salvarNovo(Produto prod) throws Exception {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        gerente.getTransaction().begin();

        if (prod.getProdCodprod() == null) {
            List<Produto> produto = consultarPorDescricao(prod.getProdDescricao());

            if (produto.isEmpty()) {
                gerente.persist(prod);

            } else {
                if (produto.get(0).getProdStatus().equals("A")) {
                    throw new Exception("Produto já cadastrado.");
                } else {
                    JOptionPane.showMessageDialog(null, "Produto indisponível. Deseja reativar o produto?");
                }
            }
        } else {
            gerente.merge(prod);
        }

        gerente.getTransaction().commit();

        gerente.close();

    }

    public void excluir(Produto codProd) {
        EntityManager gerente = GerenciadorEntidades.getGerente();
        try {
            gerente.getTransaction().begin();
            Produto p = gerente.find(Produto.class, codProd.getProdCodprod());

            p.setProdStatus("I");
            gerente.merge(p);
            gerente.getTransaction().commit();

        } finally {
            gerente.close();
        }

    }

    public List<Produto> listarTodos() {

        List<Produto> listaProd;

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Produto> consulProd;

        consulProd = gerente.createNamedQuery("Produto.findAll", Produto.class);

        consulProd.setParameter("prodStatus", "D");
        
        listaProd = consulProd.getResultList();

        return listaProd;

    }

    public Produto consultarPorCodProd(int codProd) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Produto> consulProd;

        consulProd = gerente.createNamedQuery("Produto.findByProdCodprod", Produto.class);
        consulProd.setParameter("prodCodprod", codProd);
        return consulProd.getSingleResult();
    }

    public List<Produto> consultarPorDescricao(String descricao) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Produto> consulProd;

        consulProd = gerente.createNamedQuery("Produto.findByProdDescricao", Produto.class);
        consulProd.setParameter("prodDescricao", "%" + descricao + "%");
        return consulProd.getResultList();

    }
    public List<Produto> consultarPorDescricaoIdentica(String descricao) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Produto> consulProd;

        consulProd = gerente.createNamedQuery("Produto.findByProdDescricaoIdentica", Produto.class);
        consulProd.setParameter("prodDescricao", descricao);
        return consulProd.getResultList();

    }
    
    public List<Produto> listarPorNomeProdutoDisponivel(String descricao) {
        // lista de usuários a ser retornada
        List<Produto> listaProd;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Produto> consulProd;

        // criar a consulta 
        consulProd
                = gerente.createNamedQuery("Produto.findByProdStatus", Produto.class
                );

        consulProd.setParameter("descricao", "%" + descricao + "%");

        // pegar o resultado da consulta realizada
        listaProd = consulProd.getResultList();

        return listaProd;
    }
    
}
