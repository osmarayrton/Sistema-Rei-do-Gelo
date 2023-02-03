/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderizador;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.Produto;

/**
 *
 * @author osmar
 */
public class FormatRenderer2 extends DefaultTableCellRenderer {
    
     NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    
     public Component getTableCellRendererComponentProd(JTable jtable, Produto o, boolean bln, boolean bln1, int i, int i1) {
       super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
       
       if(o.getProdPreco() instanceof Float){
           this.setText(nf.format(o.getProdPreco()));
       }
       return this;
    }
    
}
