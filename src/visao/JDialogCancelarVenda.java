package visao;

import controle.VendaDAO;
import java.awt.Frame;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ItemVenda;
import modelo.Usuario;
import modelo.Venda;

/**
 *
 * @author osmar
 */
public class JDialogCancelarVenda extends javax.swing.JDialog {

    Date hoje;
    DateFormat formatoDia = new java.text.SimpleDateFormat("dd/MM/yyyy");
    private Cliente cliente;
    Venda venda = new Venda();
    private Usuario usuario;
    VendaDAO controle = new VendaDAO();
    String Texto;
    java.text.SimpleDateFormat formatarHora = new java.text.SimpleDateFormat("HH:mm:ss");
    
     public void preencherCampos() {
        DecimalFormat fd = new DecimalFormat("#,###.00");
        txtNomeCliente.setText(cliente.getCliNome());
        txtData.setText(formatoDia.format(venda.getVndData()));
        txtFormaPagamento1.setText(venda.getVndFormapgto().toString());
        txtValorTotal1.setText("R$ " + fd.format(venda.getVndValor()).toString());
        atualizarTabela();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    

    //Atualizar Tabela
    public void atualizarTabela() {
        limparTabela();
        inserirTabela();
    }

    //Limpar Tabela
    public void limparTabela() {
        //Pegar modelo
        DefaultTableModel modelo = (DefaultTableModel) tabItemVenda.getModel();
        //Remove linha enquanto haver linha
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    //Inserir Tabela
    public void inserirTabela() {
        //Pegar modelo
        DecimalFormat fd = new DecimalFormat("#,###.00");
        DefaultTableModel modelo = (DefaultTableModel) tabItemVenda.getModel();
        //Pega todos item venda do List
        for (ItemVenda it : this.venda.getItemvendaList()) {
            Object obj[] = {it.getProduto().getProdDescricao(), it.getItvQtd(), "R$ " + fd.format(it.getItvValorUni()), "R$ " + fd.format(it.getItvQtd() * it.getItvValorUni())};
            modelo.addRow(obj);
        }
        tabItemVenda.setModel(modelo);
    }

    /**
     * Creates new form JDialogCancelarVenda
     */
    ItemVenda iv = null;

    public JDialogCancelarVenda(Frame frame, boolean bln) {

        super(frame, bln);
        initComponents();
     

    }

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labCliente = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        labData = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabItemVenda = new javax.swing.JTable();
        labItem = new javax.swing.JLabel();
        labFormaPagamento2 = new javax.swing.JLabel();
        txtFormaPagamento1 = new javax.swing.JTextField();
        labValorTotal1 = new javax.swing.JLabel();
        txtValorTotal1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusableWindowState(false);

        botCancelar.setBackground(new java.awt.Color(255, 255, 255));
        botCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/delete_remove_bin_icon-icons.com_72400.png"))); // NOI18N
        botCancelar.setText("CANCELAR");
        botCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        labCliente.setText("Cliente:");

        txtNomeCliente.setEditable(false);

        labData.setText("Data:");

        txtData.setEditable(false);

        tabItemVenda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabItemVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Quantidade", "Valor Unitário", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabItemVenda);

        labItem.setText("Itens da Venda:");

        labFormaPagamento2.setText("Forma de pagamento:");

        txtFormaPagamento1.setEditable(false);

        labValorTotal1.setText("Valor Total:");

        txtValorTotal1.setEditable(false);

        jLabel1.setText("R$");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labCliente)
                        .addGap(18, 18, 18)
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(labData)
                        .addGap(18, 18, 18)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labItem)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(labFormaPagamento2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFormaPagamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labValorTotal1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtValorTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCliente)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labData)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(labItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labFormaPagamento2)
                    .addComponent(txtFormaPagamento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labValorTotal1)
                    .addComponent(txtValorTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botCancelar)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botCancelar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCancelarActionPerformed
        // TODO add your handling code here:
        int resposta;

        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente cancelar?");
        if (resposta == JOptionPane.YES_OPTION) {
            JDialogConfirmarSenha tela = new JDialogConfirmarSenha(null, true);
            tela.setVisible(true);
            if (tela.VerificaSenha()) {
                controle.excluir(venda);
                JOptionPane.showMessageDialog(null, "Venda cancelada.");
            }

//            controle.excluir(venda);
            dispose();
        }
    }//GEN-LAST:event_botCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDialogCancelarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogCancelarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogCancelarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogCancelarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 
        //</editor-fold> 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labCliente;
    private javax.swing.JLabel labData;
    private javax.swing.JLabel labFormaPagamento2;
    private javax.swing.JLabel labItem;
    private javax.swing.JLabel labValorTotal1;
    private javax.swing.JTable tabItemVenda;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtFormaPagamento1;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtValorTotal1;
    // End of variables declaration//GEN-END:variables
}