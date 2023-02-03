/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fabrica;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceRelatorio {

    private Map<String, Object> params = new LinkedHashMap<>();

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public void abrirJasperView(String jrxml, Connection connection) {

        JasperReport report = compilarJrxml(jrxml);
        System.out.println(report);

        try {
            JasperPrint print = JasperFillManager.fillReport(report, this.params, connection);
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    private JasperReport compilarJrxml(String arquivo) {
        System.out.println(getClass().getClassLoader());
        InputStream report = getClass().getResourceAsStream(arquivo);
        if (report != null) {
            try {
                return JasperCompileManager.compileReport(report);
            } catch (JRException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Falha ao Carregar Imagem");
        }

        return null;
    }

}
