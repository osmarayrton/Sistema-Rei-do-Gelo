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
import javax.persistence.TypedQuery;

/**
 *
 * @author Aluno
 */
public class ControleTipoUsuario {
    
    public List<Usuario> listarTodos()    
    {
        EntityManager gerente = GerenciadorEntidades.getGerente();
        
        TypedQuery<Usuario> consulta =
                gerente.createNamedQuery("Usuario.todos", Usuario.class);
        
        return consulta.getResultList();
    }

    public List<Usuario> listarPorNome(String nomeProcurar)    
    {
        EntityManager gerente = GerenciadorEntidades.getGerente();
        
        TypedQuery<Usuario> consulta =
                gerente.createNamedQuery("Usuario.buscarPorNome", Usuario.class);
        
        consulta.setParameter("texto", "%"+nomeProcurar+"%");
                
        return consulta.getResultList();
    }
    
}
