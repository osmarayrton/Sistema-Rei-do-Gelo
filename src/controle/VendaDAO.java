package controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Caixa;
import modelo.Cliente;
import modelo.Venda;
import util.GerenciadorEntidades;

/**
 *
 * @author osmar
 */
public class VendaDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaReiDoGeloPU");
        return factory.createEntityManager();
    }

    // faz a persistência de uma nova venda no banco
    // de dados
    public void salvarNovo(Venda vnd) {
        // criando um gerente de entidades - responsável por fazer operações
        // com o banco de dados - persistencia
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // inicia a transação com o banco de dados
        gerente.getTransaction().begin();
        // persiste o objeto no banco
        if (vnd.getVndCodvend() == null) {
            gerente.persist(vnd);
        } else {
            gerente.merge(vnd);
        }
        // finaliza a transação
        gerente.getTransaction().commit();
        // finaliza a conexão
        gerente.close();
    }

    /**
     * Método que apaga a pessoa do banco de dados.
     *
     * @param codV
     */
    public void excluir(Venda codVnd) {
        //List<Cliente> listaCli;  
        EntityManager gerente = GerenciadorEntidades.getGerente();
        try {
            // Inicia uma transação com o banco de dados.
            gerente.getTransaction().begin();
            Venda v = gerente.find(Venda.class, codVnd.getVndCodvend());
            v.setVndStatus("C");
            gerente.merge(v);
            gerente.getTransaction().commit();

        } finally {
            gerente.close();
        }
    }

    public List<Venda> listarTodos() {
        // lista de usuários a ser retornada
        List<Venda> listaVnd;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Venda> consulVnd;
        // criar a consulta 
        consulVnd = gerente.createNamedQuery("Venda.findAll", Venda.class);
        // pegar o resultado da consulta realizada
        consulVnd.setParameter("vndStatus", "A");
        
        listaVnd = consulVnd.getResultList();

        return listaVnd;
    }
    
     public List<Venda> listarVendasNaoPagas() {
        // lista de usuários a ser retornada
        List<Venda> listaVnd;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Venda> consulVnd;
        // criar a consulta 
        consulVnd = gerente.createNamedQuery("Venda.findNaoPagas", Venda.class);
        // pegar o resultado da consulta realizada
        consulVnd.setParameter("vndStatusPagamento", "N");
        
        listaVnd = consulVnd.getResultList();

        return listaVnd;
    }

    public List<Venda> listarParaPagamento() {
        // lista de usuários a ser retornada
        List<Venda> listaVnd;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Venda> consulVnd;
        // criar a consulta 
        consulVnd = gerente.createNamedQuery("Venda.findAll", Venda.class);
        // pegar o resultado da consulta realizada
        consulVnd.setParameter("vndStatus", "A");
        
        listaVnd = consulVnd.getResultList();

        return listaVnd;
    }
    /**
     * Método que apaga a pessoa do banco de dados.
     *
     * @param codVnd
     * @return consulVnd
     */
    public Venda consultaPorCodVnd(int codVnd) {
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Venda> consulVnd;
        // criar a consulta 
        consulVnd = gerente.createNamedQuery("Venda.findByVndCodvend", Venda.class);
        consulVnd.setParameter("vndCodvend", codVnd);

        return consulVnd.getSingleResult();
    }
     public Venda consultaPorCliente(String cliente) {
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Venda> consulVnd;
        TypedQuery<Cliente> consulCli;
        // criar a consulta 
        consulCli = gerente.createNamedQuery("Cliente.findByCliNome", Cliente.class);
        consulVnd = gerente.createNamedQuery("Venda.findByCliente", Venda.class);
        consulVnd.setParameter("clienteCliCodcli.cliNome",cliente );
//        consulVnd.setParameter("clienteCliCodcli.", codCli);
//        
        return consulVnd.getSingleResult();
    }

//    public Venda consultarPorCliente(String cliente){
//        
//    }
}
