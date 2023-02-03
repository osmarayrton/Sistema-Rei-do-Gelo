/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Caixa;
import modelo.Pagamento;
import util.GerenciadorEntidades;

/**
 *
 * @author osmar
 */
public class PagamentoDAO {
    public void gravarNovo(Pagamento Novo) {
        // Identificador para objeto que gerencia uma entidade(acesso ao banco)
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // gravar o objeto Pagamento recebido como parâmetro no banco de dados
        // iniciar transação
        gerente.getTransaction().begin();

        // persistir o objeto no banco
        gerente.persist(Novo);

        // fazer um commit (salvar realmente no banco) na transação
        gerente.getTransaction().commit();

        // finalizar a conexão
        gerente.close();

    }
    
    public List<Pagamento> listarTodos() {
        // lista de usuários a ser retornada
        List<Pagamento> listaPag;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Pagamento> consulPagamento;
        // criar a consulta 
        consulPagamento= gerente.createNamedQuery("Pagamento.findAll", Pagamento.class);
        // pegar o resultado da consulta realizada
//        consulVnd.setParameter("vndStatus", "A");
        
        listaPag = consulPagamento.getResultList();

        return listaPag;
    }

    public List<Pagamento> listarTodosAPagar() {
        // lista de usuários a ser retornada
        List<Pagamento> listaPagamento;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Pagamento> consultaPagamento;

        // criar a consulta 
        consultaPagamento = gerente.createNamedQuery("Pagamento.findAll", Pagamento.class);

        // pegar o resultado da consulta realizada
        listaPagamento = consultaPagamento.getResultList();

        return listaPagamento;
    }

    public List<Pagamento> listarTodasPagas() {
        // lista de usuários a ser retornada
        List<Pagamento> listaPagamento;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Pagamento> consultaPagamento;

        // criar a consulta 
        consultaPagamento = gerente.createNamedQuery("Pagamento.findAllContaPaga", Pagamento.class);

        // pegar o resultado da consulta realizada
        listaPagamento = consultaPagamento.getResultList();

        return listaPagamento;
    }

    public List<Pagamento> buscarNome(String nome) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Pagamento> consulta
                = gerente.createNamedQuery("Pagamento.findNome", Pagamento.class);

        consulta.setParameter("descricao", "%" + nome + "%");

        return consulta.getResultList();

    }
    
     public List<Pagamento> buscarCaixa(Caixa caixa) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Pagamento> consulta
                = gerente.createNamedQuery("Pagamento.findByCaixa", Pagamento.class);

        consulta.setParameter("caixa", caixa);

        return consulta.getResultList();

    }

    public List<Pagamento> buscarNomePagas(String nome) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Pagamento> consulta
                = gerente.createNamedQuery("Pagamento.findNomePagas", Pagamento.class);

        consulta.setParameter("descricao", "%" + nome + "%");

        return consulta.getResultList();

    }

    public List<Pagamento> buscarPorPeriodo(Date inicio, Date fim) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Pagamento> consulta = gerente.createNamedQuery("Pagamento.porPeriodo", Pagamento.class);

        consulta.setParameter("dataIni", inicio);
        consulta.setParameter("dataFim", fim);
        // JOptionPane.showMessageDialog(null, consulta);
        return consulta.getResultList();
    }

    public List<Pagamento> buscarPorPeriodoPagas(Date inicio, Date fim) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Pagamento> consulta = gerente.createNamedQuery("Pagamento.porPeriodoPagas", Pagamento.class);

        consulta.setParameter("dataIni", inicio);
        consulta.setParameter("dataFim", fim);
        // JOptionPane.showMessageDialog(null, consulta);
        return consulta.getResultList();
    }

    public List<Pagamento> porPeriodoEConta(Date inicio, Date fim, String nome) {
        EntityManager gerente = GerenciadorEntidades.getGerente();
        TypedQuery<Pagamento> consulta = gerente.createNamedQuery("Pagamento.porPeriodoConta", Pagamento.class);
        consulta.setParameter("dataIni", inicio);
        consulta.setParameter("dataFim", fim);

        consulta.setParameter("descricao", "%" + nome + "%");
        return consulta.getResultList();
    }

    public List<Pagamento> porPeriodoEContaPagas(Date inicio, Date fim, String nome) {
        EntityManager gerente = GerenciadorEntidades.getGerente();
        TypedQuery<Pagamento> consulta = gerente.createNamedQuery("Pagamento.porPeriodoContaPagas", Pagamento.class);
        consulta.setParameter("dataIni", inicio);
        consulta.setParameter("dataFim", fim);

        consulta.setParameter("descricao", "%" + nome + "%");
        return consulta.getResultList();
    }

    public void pagar(Pagamento pag) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        gerente.getTransaction().begin();

        gerente.merge(pag);

        gerente.getTransaction().commit();

        gerente.close();
    }

    public List<Pagamento> listarTodosConta() {
        // lista de usuários a ser retornada
        List<Pagamento> listaPagamento;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Pagamento> consultaPagamento;

        // criar a consulta 
        consultaPagamento = gerente.createNamedQuery("Pagamento.contaSemCompra", Pagamento.class);

        // pegar o resultado da consulta realizada
        listaPagamento = consultaPagamento.getResultList();

        return listaPagamento;
    }

    public void salvarAlteracao(Pagamento pag) {
        // criar um gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // iniciar uma transação com o banco
        gerente.getTransaction().begin();

        //salvar a alteração no banco de dados
        // merge sobrepõe um registro no banco de dados com base na 
        //chave primária da entidade
        gerente.merge(pag);

        // finalizar a transação com o banco
        gerente.getTransaction().commit();

        // fecho a conexão
        gerente.close();

    }

    public void excluir(Pagamento p){
        
        // cria uma conexão com o banco
        EntityManager gerente = GerenciadorEntidades.getGerente();
        
        boolean encontrado;
        // criando um identificador para um objeto usuário
        
        
        // pego o usuário que possui o código passado do banco de
        // dados
        
        
        
            
            // inicia uma transação
            gerente.getTransaction().begin();
            
            //excluir o usuário do banco de dados
            gerente.remove(gerente.merge(p));
            
            // finaliza a transação
            gerente.getTransaction().commit();
            
            // fechar a conexão
            gerente.close();
            
        
        
       
    }
    
    
    public List<Pagamento> buscarPorPeriodoRelatorio(Date inicio, Date fim) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Pagamento> consulta = gerente.createNamedQuery("Pagamento.porPeriodoaPagarRelatorio", Pagamento.class);

        consulta.setParameter("dataIni", inicio);
        consulta.setParameter("dataFim", fim);
        // JOptionPane.showMessageDialog(null, consulta);
        return consulta.getResultList();
    }
    
    
    
    
   
    
}
