/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderizador;

/**
 *
 * @author osmar
 */
import java.awt.Component;
import java.text.Format;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.Produto;
 
/**
 * @author  Roberto Silva 
 * @brief   classe é responsável pela formatação de data, hora
 */
public class FormatRenderer extends DefaultTableCellRenderer {
 
    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
       super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
       
       if(o instanceof Float){
           this.setText(nf.format((Float)o));
       }
       return this;
    }
   
}
