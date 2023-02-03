/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.*;
import util.GerenciadorEntidades;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class UsuarioDAO {

    public static Usuario usuarioLogado = null;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public static Usuario isUsuarioLogado() {
        if (usuarioLogado == null) {
            return null;
        } else {
            return usuarioLogado;
        }
    }

    public void logoutUsuario() {
        this.usuarioLogado = null;
    }

    public Usuario logarUsuario(String email, String senha) {
        Usuario usuarioAutenticado;

        EntityManager gerente = GerenciadorEntidades.getGerente();

        List<Usuario> listaUsuarios;

        TypedQuery<Usuario> consultaUsuario = gerente.createNamedQuery("Usuario.acessar", Usuario.class);

        consultaUsuario.setParameter("email", email);
        consultaUsuario.setParameter("senha", senha);

        listaUsuarios = consultaUsuario.getResultList();

        if (listaUsuarios.isEmpty()) {
            this.usuarioLogado = null;
        } else {
            this.usuarioLogado = listaUsuarios.get(0);
            System.out.println("" + usuarioLogado);
        }

        return this.usuarioLogado;
    }

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaReiDoGeloPU");
        return factory.createEntityManager();
    }
     public List<Usuario> consultarPorEmailUs(String email) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Usuario> consulUs;

        // criar a consulta 
        consulUs
                = gerente.createNamedQuery("Usuario.findByUsEmail", Usuario.class
                );
        consulUs.setParameter("usEmail", email);
        return consulUs.getResultList();
    }
    // faz a persistência de um novo usuário no banco
    // de dados
    public void salvarNovo(Usuario us) throws Exception 
    {
        //System.out.println(us.toString());
        // criando um gerente de entidades - responsável por fazer operações
        // com o banco de dados - persistencia
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // inicia a transação com o banco de dados
        gerente.getTransaction().begin();

        // persiste o objeto no banco
        if (us.getUsCodusuario() == null) {
            //System.out.println("Inserir");
            //Usuario usu = new Usuario();
            List<Usuario> usuario = consultarPorEmailUs(us.getUsEmail());
                //System.out.println("usuario: "+ usuario.size());
            if (usuario.isEmpty()) {
                
                gerente.persist(us);
            } else {
                if (usuario.get(0).getUsStatus().equals("A")) {
                    throw new Exception("Usuário já cadastrado.");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário inativo. Deseja reativar o usuário?");
                    
                }
            }
        } else {
            gerente.merge(us);
        }
        // finaliza a transação
        gerente.getTransaction().commit();

        // finaliza a conexão
        gerente.close();
    }

// esse método tem como objetivo buscar um usuário utilizando
// o seu código - Retorna um objeto usuário ou null caso não exista
/*
    Método para excluir um usuário a partir do código
     */
    public void excluir(Usuario codUs) {

        // cria uma conexão com o banco
        EntityManager gerente = GerenciadorEntidades.getGerente();

        try {
            // Inicia uma transação com o banco de dados.
            gerente.getTransaction().begin();
            Usuario u = gerente.find(Usuario.class, codUs.getUsCodusuario());
            // Consulta a pessoa na base de dados através do seu ID.
            u.setUsStatus("I");
            gerente.merge(u);
            gerente.getTransaction().commit();
        } finally {
            gerente.close();
        }

    }
    
    public List<Usuario> listarPorNomeUsuarioAtivo(String nome) {
        // lista de usuários a ser retornada
        List<Usuario> listaUs;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Usuario> consulUs;

        // criar a consulta 
        consulUs
                = gerente.createNamedQuery("Usuario.findByNomeStatus", Usuario.class
                );

        consulUs.setParameter("A", "A");
        consulUs.setParameter("nome", "%" + nome + "%");

        // pegar o resultado da consulta realizada
        listaUs = consulUs.getResultList();

        return listaUs;
    }

    public List<Usuario> listarTodos() {
        // lista de usuários a ser retornada
        List<Usuario> listaUs;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Usuario> consulUs;

        // criar a consulta 
        consulUs
                = gerente.createNamedQuery("Usuario.findAll", Usuario.class
                );
        
        consulUs.setParameter("usStatus", "A");

        // pegar o resultado da consulta realizada
        listaUs = consulUs.getResultList();

        return listaUs;
    }

    public Usuario consultarPorCodUs(int codUs) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Usuario> consulUs;

        // criar a consulta 
        consulUs
                = gerente.createNamedQuery("Usuario.findByUsCodus", Usuario.class
                );
        consulUs.setParameter("usCodus", codUs);
        return consulUs.getSingleResult();
    }

    public List<Usuario> consultarPorNome(String nome) {

        EntityManager gerente = GerenciadorEntidades.getGerente();

        TypedQuery<Usuario> consulUs;

        // criar a consulta 
        consulUs
                = gerente.createNamedQuery("Usuario.findByUsNome", Usuario.class
                );
        consulUs.setParameter("usNome", "%" + nome + "%");
        consulUs.setParameter("usStatus", "A");
        return consulUs.getResultList();
    }

    /**
     * Método que retorna a quantidade de usuários cadastrados no banco de dados
     *
     * @return inteiro contendo a quantidade de usuários
     */
    public long contarUs() {
        long qtdUs = 0;

        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = GerenciadorEntidades.getGerente();

        // Criar um identificar para uma consulta SQL
        TypedQuery<Long> consulUs;

        // criar a consulta que retornará um Inteiro
        consulUs
                = gerente.createNamedQuery("Usuario.contUs",
                        Long.class
                );

        // pegar o resultado da consulta, que é um inteiro
        qtdUs = consulUs.getSingleResult();

        return qtdUs;
    }

    /* Login  */
    public Usuario findAcha(String email, String senha) {
        EntityManager em = GerenciadorEntidades.getGerente();
        Query query = em.createNamedQuery("Usuario.achar", Usuario.class
        );
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return (Usuario) query.getSingleResult();
    }

    public long conta() {
        EntityManager em = GerenciadorEntidades.getGerente();
        Query query = em.createNamedQuery("Usuario.conta", Usuario.class
        );
        long conta = (long) query.getSingleResult();
        return conta;
    }
}
