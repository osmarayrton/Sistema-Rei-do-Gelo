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
import modelo.Caixa;
import modelo.Despesa;
import modelo.Pagamento;
import modelo.TipoDespesa;
import util.GerenciadorEntidades;

/**
 *
 * @author Mauricio
 */
public class DespesaDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaReiDoGeloPU");
        return factory.createEntityManager();
    }

    public void salvarNovo(Despesa desp) {

        EntityManager gerente = GerenciadorEntidades.getGerente();
        
        try{
        gerente.getTransaction().begin();

        if (desp.getDespCoddesp() == null) {
            gerente.persist(desp);
        } else {
            gerente.merge(desp);
        }

        // finaliza a transação
        gerente.getTransaction().commit();

        
        } finally{
            // finaliza a conexão
            gerente.close();
            
        }
        
    }

    public void excluir(Despesa codDesp) {

        EntityManager gerente = GerenciadorEntidades.getGerente();
        try {
            gerente.getTransaction().begin();
            Despesa d = gerente.find(Despesa.class,codDesp.getDespCoddesp());
            
            d.setDespStatus("I");
            gerente.merge(d);
           
            // Finaliza a transação.
            gerente.getTransaction().commit();
        } finally {
            gerente.close();
        }
    }
    
        public List<Despesa> listarTodos() {
        // lista de usuários a ser retornada
        List<Despesa> listaDespesas;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Despesa> consulDesp;
        
        TypedQuery<TipoDespesa> consulTipoDesp;

        // criar a consulta 
        consulDesp = gerente.createNamedQuery("Despesa.findAll", Despesa.class);
        
        consulDesp.setParameter("despStatus", "A");
       

        // pegar o resultado da consulta realizada
        listaDespesas = consulDesp.getResultList();

        return listaDespesas;
    }
        
          
     public List<Despesa> consultarPorNome(String desc) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        /*codCli = null;
    try {
      //Consulta uma pessoa pelo seu ID.
      codCli = gerente.find(Cliente.class, codCli);
    } finally {
      gerente.close();
    }*/
        // Criar um identificar para uma consulta SQL
        TypedQuery<Despesa> consulDesp;

        // criar a consulta 
        consulDesp = gerente.createNamedQuery("Despesa.findByDespDescricao", Despesa.class);
        consulDesp.setParameter("despDescricao", "%" + desc + "%" );
        return consulDesp.getResultList();
    }
     
      public List<Despesa> buscarCaixa(Caixa caixa) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Despesa> consulta
                = gerente.createNamedQuery("Despesa.findByCaixa", Despesa.class);

        consulta.setParameter("caixa", caixa);

        return consulta.getResultList();

    }

} // ultimo

