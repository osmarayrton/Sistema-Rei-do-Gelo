/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import util.GerenciadorEntidades;

/**
 *
 * @author osmar
 */
public class FabricaRelatorio {
    
    
    

        
        public static void abrirJrxm(String url)  {
            EntityManager gerente = GerenciadorEntidades.getGerente();
            Connection connection = null;
            try {
                connection = ConnectionFactory.createConnection();
                ServiceRelatorio service = new ServiceRelatorio();
                service.abrirJasperView(url, connection);
            } catch (SQLException ex) {
                Logger.getLogger(FabricaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
//            service.abrirJasperView("/org/openjfx/relatorios/jrxml/Colaboradores2.jrxml", connection);

            

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        
        public static void abrirJrxmCaixa(String url, Date data)  {
            EntityManager gerente = GerenciadorEntidades.getGerente();
            Connection connection = null;
            try {
                connection = ConnectionFactory.createConnection();
                ServiceRelatorio service = new ServiceRelatorio();
                service.addParams("data1" ,data );
                service.abrirJasperView(url, connection);
            } catch (SQLException ex) {
                Logger.getLogger(FabricaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
//            service.abrirJasperView("/org/openjfx/relatorios/jrxml/Colaboradores2.jrxml", connection);

            

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        public static void abrirJrxmRecebimento(String url, Date data1, Date data2)  {
            EntityManager gerente = GerenciadorEntidades.getGerente();
            Connection connection = null;
            try {
                connection = ConnectionFactory.createConnection();
                ServiceRelatorio service = new ServiceRelatorio();
                service.addParams("data1" ,data1 );
                service.addParams("data2", data2);
                service.abrirJasperView(url, connection);
            } catch (SQLException ex) {
                Logger.getLogger(FabricaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
             try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        
        public static void abrirJrxmVenda(String url, Date data1, Date data2)  {
            EntityManager gerente = GerenciadorEntidades.getGerente();
            Connection connection = null;
            try {
                connection = ConnectionFactory.createConnection();
                ServiceRelatorio service = new ServiceRelatorio();
                service.addParams("data1" ,data1 );
                service.addParams("data2", data2);
                service.abrirJasperView(url, connection);
            } catch (SQLException ex) {
                Logger.getLogger(FabricaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
             try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
       
        
        
    }
       
        
//        
        
    

