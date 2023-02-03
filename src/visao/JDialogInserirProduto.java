/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ProdutoDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Produto;

/**
 *
 * @author osmar
 */
public class JDialogInserirProduto extends javax.swing.JDialog {

    Produto produto = new Produto();
    Produto produtoEditar = new Produto();
    ProdutoDAO controle = new ProdutoDAO();
    TableModel model = new TableModel();
    private boolean editar;

    /**
     * Creates new form JDialogInsProduto
     */
    public JDialogInserirProduto(Frame frame, boolean bln, Produto produto) {

        super(frame, bln);
        initComponents();

        if (produto == null) {
            liberarCampos(true);
        } else {
            this.editar = true;
            txtDescricao.setText(produto.getProdDescricao());
            formatadoPreco.setText(String.format("%s", produto.getProdPreco()));
            liberarCampos(false);
            this.produtoEditar = produto;
            this.produto = produto;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        labPreco = new javax.swing.JLabel();
        botSalvar = new javax.swing.JButton();
        formatadoPreco = new javax.swing.JFormattedTextField();
        botEditar = new javax.swing.JButton();
        botExcluir = new javax.swing.JButton();
        lab = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labDescricao.setText("Descrição:");

        txtDescricao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescricaoFocusLost(evt);
            }
        });
        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyReleased(evt);
            }
        });

        labPreco.setText("Preço:");

        botSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-salvar-30.png"))); // NOI18N
        botSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botSalvarActionPerformed(evt);
            }
        });

        formatadoPreco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));

        botEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-lápis-24.png"))); // NOI18N
        botEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botEditarActionPerformed(evt);
            }
        });

        botExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-fechar-janela-24.png"))); // NOI18N
        botExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botExcluirActionPerformed(evt);
            }
        });

        lab.setText("R$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labPreco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formatadoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botEditar)
                .addGap(18, 18, 18)
                .addComponent(botExcluir)
                .addGap(18, 18, 18)
                .addComponent(botSalvar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPreco)
                    .addComponent(formatadoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void liberarCampos(boolean a) {
        for (Component c : jPanel1.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setEditable(a);
            } else if (c instanceof JLabel) {

            } else {

                c.setEnabled(a);
            }

        }

        botSalvar.setEnabled(a);
        botEditar.setEnabled(!a);
        botExcluir.setEnabled(!a);

    }
    private void botSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botSalvarActionPerformed
        // TODO add your handling code here:
        // produto.setProdCodprod(Integer.parseInt(txtCod.getText()));

        if (txtDescricao.getBackground().getRGB() == -65536) {
            JOptionPane.showMessageDialog(this, "Já existe um cadastro para esse produto.");
        } else {
        
        if(editar){
            produtoEditar.setProdDescricao(txtDescricao.getText());
        produtoEditar.setProdPreco(Float.parseFloat(formatadoPreco.getText().replace(",", ".")));
        produtoEditar.setProdStatus("D");
        }else{
            this.produto = new Produto();
        
        produto.setProdDescricao(txtDescricao.getText());
        produto.setProdPreco(Float.parseFloat(formatadoPreco.getText().replace(",", ".")));
        produto.setProdStatus("D");
        }
        
        if(txtDescricao.getText().isEmpty()){
             JOptionPane.showMessageDialog(this, "Não preencheu o campo obrigatório: Descrição.");
            return;
        }
        if(formatadoPreco.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Não preencheu o campo obrigatório: Preço.");
            return;
        }
        
//      
        try {
           if(editar){
               controle.salvarNovo(produtoEditar);
               JOptionPane.showMessageDialog(null, "Produto Editado");
           }else{
            controle.salvarNovo(produto);
            JOptionPane.showMessageDialog(null, "Produto Salvo");
           }
            
            
            dispose();
        } catch (HeadlessException ex) {
            System.out.println(ex);
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(JDialogInserirProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

      }

    }//GEN-LAST:event_botSalvarActionPerformed

    private void botEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botEditarActionPerformed
        // TODO add your handling code here:
        liberarCampos(true);

    }//GEN-LAST:event_botEditarActionPerformed

    private void botExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botExcluirActionPerformed
        // TODO add your handling code here:
        liberarCampos(true);
        int resposta;

        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir?");
        if (resposta == JOptionPane.YES_OPTION) {

            controle.excluir(produto);
            dispose();
        }
    }//GEN-LAST:event_botExcluirActionPerformed

    private void txtDescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyReleased
         
        int opcao = -1;

        List<Produto> produto1 = controle.consultarPorDescricaoIdentica(txtDescricao.getText());

        if (!produto1.isEmpty()) {
            int cont = 0;
            int cont2 = 0;

            for (Produto p : produto1) {
                if (p.getProdStatus().equals("D")) {
                    cont += 1;
                    this.produto = p;
                } else if (p.getProdStatus().equals("I")) {
                    cont2 += 1;
                    this.produto = p;
                }
            }

            if (cont > 0) {
                txtDescricao.setBackground(Color.red);
                //JOptionPane.showMessageDialog(this, "Já existe um cadastro para esse produto.");
                //txtDescricao.setText(produto.getProdDescricao());
                formatadoPreco.setText(String.format("%s", produto.getProdPreco()));
            } else {
                txtDescricao.setBackground(Color.white);
            }

            if (produto.getProdStatus().equals("I")) {
                opcao = JOptionPane.showConfirmDialog(this, "Deseja reativar o produto?", "Produto indisponível", 0);

                if (opcao == 0) {
                    txtDescricao.setBackground(Color.white);
                    produtoEditar = produto;
                    
                    editar=true;
                    txtDescricao.setText(produto.getProdDescricao());
                    formatadoPreco.setText(String.format("%s", produto.getProdPreco()));
                }
                else {
                    txtDescricao.setBackground(Color.red);
                    txtDescricao.setText(produto.getProdDescricao());
                    formatadoPreco.setText(String.format("%s", produto.getProdPreco()));
                }
            }
        } else {
            txtDescricao.setBackground(Color.white);
        }

        System.out.println("cor: " + txtDescricao.getBackground().getRGB());
        

    }//GEN-LAST:event_txtDescricaoKeyReleased

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void txtDescricaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescricaoFocusLost
        // TODO add your handling code here:
//        int opcao = -1;
//
//        List<Produto> produto1 = controle.consultarPorDescricao(txtDescricao.getText());
//
//        if (!produto1.isEmpty() && !editar) {
//            int cont = 0;
//            int cont2 = 0;
//
//            for (Produto p : produto1) {
//                if (p.getProdStatus().equals("D")) {
//                    cont += 1;
//                    this.produto = p;
//                } else if (p.getProdStatus().equals("I")) {
//                    cont2 += 1;
//                    this.produto = p;
//                }
//            }
//
//            if (cont > 0) {
//                txtDescricao.setBackground(Color.red);
//                JOptionPane.showMessageDialog(this, "Já existe um cadastro para esse produto.");
//                txtDescricao.setText(produto.getProdDescricao());
//                formatadoPreco.setText(String.format("%s", produto.getProdPreco()));
//                botSalvar.setEnabled(false);
//            } else {
//                txtDescricao.setBackground(Color.white);
//                botSalvar.setEnabled(true);
//
//            }
//
//            if (produto.getProdStatus().equals("I")) {
//                opcao = JOptionPane.showConfirmDialog(this, "Deseja reativar o produto?", "Produto indisponível", 0);
//
//                if (opcao == 0) {
//                    txtDescricao.setBackground(Color.red);
//                    txtDescricao.setText(produto.getProdDescricao());
//                    formatadoPreco.setText(String.format("%s", produto.getProdPreco()));
//                }
////                else {
////                    txtDescricao.setBackground(Color.white);
////                }
//            }
//        }
//        if (!txtDescricao.getText().equals(produto.getProdDescricao())) {
//
//            List<Produto> produto2 = controle.consultarPorDescricao(txtDescricao.getText());
//
//            if (!produto2.isEmpty()) {
//
//                int cont = 0;
//                int cont2 = 0;
//
//                for (Produto p : produto1) {
//                    if (p.getProdStatus().equals("D")) {
//                        cont += 1;
//                        this.produto = p;
//                    } else if (p.getProdStatus().equals("I")) {
//                        cont2 += 1;
//                        this.produto = p;
//                    }
//                }
//
//                if (cont > 0) {
//                    txtDescricao.setBackground(Color.red);
//                    JOptionPane.showMessageDialog(this, "Já existe um cadastro para esse produto.");
//                    txtDescricao.setText(produto.getProdDescricao());
//                    formatadoPreco.setText(String.format("%s", produto.getProdPreco()));
//
//                } else {
//                    txtDescricao.setBackground(Color.white);
//                }
//
//                if (produto.getProdStatus().equals("I")) {
//                    opcao = JOptionPane.showConfirmDialog(this, "Deseja reativar o produto?", "Produto indisponível", 0);
//
//                    if (opcao == 0) {
//                        txtDescricao.setBackground(Color.red);
//                        txtDescricao.setText(produto.getProdDescricao());
//                        formatadoPreco.setText(String.format("%s", produto.getProdPreco()));
//                    }
////                else {
////                    txtDescricao.setBackground(Color.white);
////                }
//                }
//            }else {
//                txtDescricao.setBackground(Color.white);
//                botSalvar.setEnabled(true);
//
//            }
//        } else {
//            txtDescricao.setBackground(Color.white);
//        }
    }//GEN-LAST:event_txtDescricaoFocusLost

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
            java.util.logging.Logger.getLogger(JDialogInserirProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogInserirProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogInserirProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogInserirProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botEditar;
    private javax.swing.JButton botExcluir;
    private javax.swing.JButton botSalvar;
    private javax.swing.JFormattedTextField formatadoPreco;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lab;
    private javax.swing.JLabel labDescricao;
    private javax.swing.JLabel labPreco;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables
}
