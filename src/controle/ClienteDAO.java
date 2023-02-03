package controle;

import modelo.Cliente;
import util.GerenciadorEntidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author osmar
 */
public class ClienteDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaReiDoGeloPU");
        return factory.createEntityManager();
    }

    // faz a persistência de um novo cliente no banco
    // de dados
    public void salvarNovo(Cliente cli) throws Exception {
        // criando um gerente de entidades - responsável por fazer operações
        // com o banco de dados - persistencia
        EntityManager gerente = GerenciadorEntidades.getGerente();
        // inicia a transação com o banco de dados
        gerente.getTransaction().begin();
        //  persiste o objeto no banco
        if (cli.getCliCodcli() == null) {
            Cliente cliente = consultarPorCPF(cli.getCliCpfCnpj());
            Cliente cliente1 = consultarPorCNPJ(cli.getCliCpfCnpj());
            if (cliente == null) {
                gerente.persist(cli);
            }
            if (cliente1 == null) {
                gerente.persist(cli);

            } else {
                if (cliente.getCliStatus().equals("A")) {
//                    JOptionPane.showMessageDialog(null, "CPF ou CNPJ já cadastrado.");
                    throw new Exception("CPF ou CNPJ já cadastrado.");
                } else {
                    JOptionPane.showMessageDialog(null, "CPF  ou CNPJ inativo. Deseja reativar o cliente?");
                    //REATIVAR
                }
                if (cliente1.getCliStatus().equals("A")) {
//                    JOptionPane.showMessageDialog(null, "CPF ou CNPJ já cadastrado.");
                    throw new Exception("CPF ou CNPJ já cadastrado.");
                } else {
                    JOptionPane.showMessageDialog(null, "CPF  ou CNPJ inativo. Deseja reativar o cliente?");
                    //REATIVAR
                }
            }
        } else {
            gerente.merge(cli);
            System.out.println("Atualizar");
        }
        // finaliza a transação
        gerente.getTransaction().commit();
        // finaliza a conexão
        gerente.close();
    }

    /**
     * Método que apaga a pessoa do banco de dados.
     *
     * @param codCli
     */
    public void excluir(Cliente codCli) {

        //List<Cliente> listaCli;
        EntityManager gerente = GerenciadorEntidades.getGerente();
        try {
            // Inicia uma transação com o banco de dados.
            gerente.getTransaction().begin();
            Cliente c = gerente.find(Cliente.class, codCli.getCliCodcli());
            // Consulta a pessoa na base de dados através do seu ID.
            // Remove a pessoa da base de dados.
//            if (!gerente.contains(codCli)) {
            c.setCliStatus("I");
            gerente.merge(c);

//            gerente.remove(codCli);
            // Finaliza a transação.
            gerente.getTransaction().commit();
        } finally {
            gerente.close();

        }

    }

    public List<Cliente> listarPorNomeClienteAtivo(String nome) {
        // lista de usuários a ser retornada
        List<Cliente> listaCli;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Cliente> consulCli;

        // criar a consulta 
        consulCli
                = gerente.createNamedQuery("Cliente.findByNomeStatus", Cliente.class
                );

        consulCli.setParameter("A", "A");
        consulCli.setParameter("nome", "%" + nome + "%");

        // pegar o resultado da consulta realizada
        listaCli = consulCli.getResultList();

        return listaCli;
    }

    public List<Cliente> listarTodos() {
        // lista de usuários a ser retornada
        List<Cliente> listaCli;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Cliente> consulCli;

        // criar a consulta 
        consulCli
                = gerente.createNamedQuery("Cliente.findAll", Cliente.class
                );

        consulCli.setParameter("cliStatus", "A");

        // pegar o resultado da consulta realizada
        listaCli = consulCli.getResultList();

        return listaCli;
    }

    /**
     *
     * @param codCli
     * @return
     */
    public Cliente consultarPorCodCli(int codCli) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Cliente> consulCli;

        // criar a consulta 
        consulCli
                = gerente.createNamedQuery("Cliente.findByCliCodcli", Cliente.class
                );
        consulCli.setParameter("cliCodcli", codCli);
        return consulCli.getSingleResult();
    }

    public List<Cliente> consultarPorNome(String nome, String status) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Cliente> consulCli;

        // criar a consulta 
        consulCli
                = gerente.createNamedQuery("Cliente.findByCliNome", Cliente.class);
        consulCli.setParameter("cliNome", "%" + nome + "%");
        consulCli.setParameter("cliStatus", status);
        return consulCli.getResultList();
    }

    public Cliente consultarPorCPF(String cpf) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Cliente> consulCli;

        // criar a consulta 
        consulCli = gerente.createNamedQuery("Cliente.findByCliCpfCnpj", Cliente.class);
        consulCli.setParameter("cliCpfCnpj", cpf);
        try {
            return consulCli.getSingleResult();
        } catch (NoResultException Ex) {

            return null;
        }
    }

    public Cliente consultarPorCNPJ(String cnpj) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Cliente> consulCli;

        // criar a consulta 
        consulCli = gerente.createNamedQuery("Cliente.findByCliCpfCnpj", Cliente.class);
        consulCli.setParameter("cliCpfCnpj", cnpj);
        try {
            return consulCli.getSingleResult();
        } catch (NoResultException Ex) {
            return null;
        }
    }

    /**
     * Método que retorna a quantidade de usuários cadastrados no banco de dados
     *
     * @return inteiro contendo a quantidade de usuários
     */
    public long contarCli() {
        long qtdCli = 0;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Long> consulCli;

        // criar a consulta que retornará um Inteiro
        consulCli
                = gerente.createNamedQuery("Cliente.contCli",
                        Long.class
                );

        // pegar o resultado da consulta, que é um inteiro
        qtdCli = consulCli.getSingleResult();

        return qtdCli;
    }

}
