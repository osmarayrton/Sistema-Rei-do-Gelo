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
import modelo.TipoDespesa;
import modelo.TipoDespesa_;
import util.GerenciadorEntidades;

/**
 *
 * @author Mauricio
 */
public class TipoDespesaDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaReiDoGeloPU");
        return factory.createEntityManager();
    }

    public void salvarNovo(TipoDespesa tipo_desp) {
        EntityManager gerente = GerenciadorEntidades.getGerente();

        try {
            gerente.getTransaction().begin();

            if (tipo_desp.getTpdespCod() == null) {
                gerente.persist(tipo_desp);

            } else {
                gerente.merge(tipo_desp);
            }

            //finaliza a transação
            gerente.getTransaction().commit();

        } finally { //finaliza a conexão
            gerente.close();

        }
    }
    
    

    public void excluir(TipoDespesa codTipo_desp) {
        EntityManager gerente = GerenciadorEntidades.getGerente();
        try {
            gerente.getTransaction().begin();
            TipoDespesa tpd = gerente.find(TipoDespesa.class,codTipo_desp.getTpdespCod());
            tpd.setTpdespStatus("I");
            gerente.merge(tpd);
            
            // Finaliza a transação.
            gerente.getTransaction().commit();
        } finally {
            gerente.close();
        }

    }

    public List<TipoDespesa> listarTodos() {
        //lista de tipo de despesas a ser retornada
        List<TipoDespesa> listaTipoDespesas;

        //cria uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        //Criar um identificar para uma consulta SQL
        TypedQuery<TipoDespesa> consulTipoDespesa;

        //criar a consulta
        consulTipoDespesa = gerente.createNamedQuery("TipoDespesa.findAll", TipoDespesa.class);
        
        consulTipoDespesa.setParameter("tpdespStatus", "A");

        //pegar o resultado da consulta realizada
        listaTipoDespesas = consulTipoDespesa.getResultList();

        return listaTipoDespesas;
    }

    public List<TipoDespesa> consultarPorDescricao(String desc) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        //Criar um identificar para uma consulta SQL
        TypedQuery<TipoDespesa> consulTipDespesa;

        //criar a consulta
        consulTipDespesa = gerente.createNamedQuery("Tipodespesa.findByTpdespDescricao", TipoDespesa.class);
        consulTipDespesa.setParameter("tipoDespDescricao", "%" + desc + "%");
        return consulTipDespesa.getResultList();
    }

}
