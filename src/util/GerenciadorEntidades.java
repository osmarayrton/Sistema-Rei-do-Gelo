/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Aluno
 */
public class GerenciadorEntidades {
    
    private static EntityManagerFactory emf;
    
    public static EntityManagerFactory getFabricaGerente()
    {
        if(emf == null)
        {
           emf = Persistence.createEntityManagerFactory("SistemaReiDoGeloPU");
        }
        return emf;
    }
    
    public static EntityManager getGerente()
    {
//        EntityManager gerente;
//        if(emf == null)
//        {
//            emf = Persistence.createEntityManagerFactory("AulaJPAPU");
//        }
//
//        gerente = emf.createEntityManager();        
//        return gerente;
        
        // tudo isso ou somente isso
        
        return getFabricaGerente().createEntityManager();
    }
    
}
