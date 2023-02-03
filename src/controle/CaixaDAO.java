/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Caixa;
import util.GerenciadorEntidades;

/**
 *
 * @author osmar
 */
public class CaixaDAO {
    
    private static Caixa caixaAberto = null;
    
    public static Caixa getCaixa() {
        return caixaAberto;
    }
    
    public static void setCaixa(Caixa caixaAberto){
        CaixaDAO.caixaAberto = caixaAberto;
    }
    
    public static Caixa isCaixaAberto(){
        if(caixaAberto == null){
            return null;
        }else{
            return caixaAberto;
        }
    }
    
    public static void caixaAberto(){
        CaixaDAO.caixaAberto = null;
    }
    
    public void gravarCaixa(Caixa x){
        // Identificador para objeto que gerencia uma entidade(acesso ao banco)
        EntityManager gerente = GerenciadorEntidades.getGerente();
        
        gerente.getTransaction().begin();
        
        gerente.persist(x);
        
        gerente.getTransaction().commit();
        
        gerente.close();
    }
    
    public List<Caixa> buscar(){
        List<Caixa> listaCaixa;
        
        EntityManager gerente = GerenciadorEntidades.getGerente();
        
        TypedQuery<Caixa> consultaCaixa;
      
        consultaCaixa =  gerente.createNamedQuery("Caixa.buscaAberto", Caixa.class);
        
        listaCaixa = consultaCaixa.getResultList();
        
        return listaCaixa;
    }
    
    public void salvarAlteracao (Caixa caixa){
        EntityManager gerente = GerenciadorEntidades.getGerente();
        
        gerente.getTransaction().begin();
        
        gerente.merge(caixa);
        
        gerente.getTransaction().commit();
        
        gerente.close();
    } 
    
    //cria um método 
    
    
    
}
