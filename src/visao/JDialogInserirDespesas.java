/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.CaixaDAO;
import controle.DespesaDAO;
import controle.TipoDespesaDAO;
import controle.UsuarioDAO;
import java.awt.Component;
import java.awt.Frame;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Despesa;
import modelo.Usuario;
import modelo.TipoDespesa;

/**
 *
 * @author osmar
 */
public class JDialogInserirDespesas extends javax.swing.JDialog {

    Despesa despesa = new Despesa();
    DespesaDAO controle = new DespesaDAO();
    TipoDespesaDAO controleTipoDespesa = new TipoDespesaDAO();
    List<Despesa> listTipoDesp = new ArrayList<>();
    List<Usuario> listUsuario = new ArrayList<>();
    UsuarioDAO controleUs = new UsuarioDAO();
    private List<TipoDespesa> listTp = new ArrayList<>();
    DecimalFormat fd = new DecimalFormat("#,###.00");

    /**
     * Creates new form JDialogInsDespesas
     */
    public JDialogInserirDespesas(Frame frame, boolean bln, Despesa desp) {

        super(frame, bln);
        initComponents();
        preencheComboTipoDespesa();

        if (desp == null) {
            liberarCampos(true);

        } else {

            this.despesa = desp;
//            txtValor.setText(String.format("%s", despesa.getDespValor()));
            listTipoDesp = controle.listarTodos();
            listUsuario = controleUs.listarTodos();
            int despesaint = 0;
            int usuarioint = 0;
            for (Despesa d : listTipoDesp) {
                if (d.getTipodespesaTpdespCod().getTpdespDescricao() == this.despesa.getTipodespesaTpdespCod().getTpdespDescricao()) {
                    despesaint = d.getTipodespesaTpdespCod().getTpdespCod();
                }
            }
            for (Usuario u : listUsuario) {
                if (u.getUsCodusuario() == this.despesa.getUsuarioUsCodusuario().getUsCodusuario()) {
                    usuarioint = u.getUsCodusuario();
                }
            }
            usuarioint = usuarioint - 1;
            despesaint = despesaint - 2;

            comboUsuario.setSelectedIndex(usuarioint);
            comboTipoDespesa.setSelectedIndex(despesaint);

            System.out.println(" Usu??rio e Descri????o: " + despesa.getTipodespesaTpdespCod().getTpdespDescricao() + despesa.getUsuarioUsCodusuario().getUsNome());
            txtValorPago.setText(fd.format(despesa.getDespVlPago()));
            dataDataPag.setDate(despesa.getDespDataPagamento());
            dataDataVenc.setDate(despesa.getDespDataVencimento());
            liberarCampos(false);

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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        SistemaReiDoGeloPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SistemaReiDoGeloPU").createEntityManager();
        tipodespesaQuery = java.beans.Beans.isDesignTime() ? null : SistemaReiDoGeloPUEntityManager.createQuery("SELECT t FROM TipoDespesa t");
        tipodespesaList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : tipodespesaQuery.getResultList();
        usuarioQuery = java.beans.Beans.isDesignTime() ? null : SistemaReiDoGeloPUEntityManager.createQuery("SELECT u FROM Usuario u");
        usuarioList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : usuarioQuery.getResultList();
        jPanel1 = new javax.swing.JPanel();
        labTipo = new javax.swing.JLabel();
        labVencimento = new javax.swing.JLabel();
        labDataPag = new javax.swing.JLabel();
        labValorPago = new javax.swing.JLabel();
        txtValorPago = new javax.swing.JTextField();
        dataDataVenc = new org.netbeans.modules.form.InvalidComponent();
        dataDataPag = new org.netbeans.modules.form.InvalidComponent();
        comboTipoDespesa = new javax.swing.JComboBox<>();
        comboUsuario = new javax.swing.JComboBox<>();
        labUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        botSalvar = new javax.swing.JButton();
        botEditar = new javax.swing.JButton();
        botExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labTipo.setText("Tipo de Despesa:");

        labVencimento.setText("Data Vencimento:");

        labDataPag.setText("Data Pagamento:");

        labValorPago.setText("Valor Pago:");

        comboTipoDespesa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        comboUsuario.setBackground(new java.awt.Color(204, 204, 204));
        comboUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, usuarioList, comboUsuario);
        bindingGroup.addBinding(jComboBoxBinding);

        labUsuario.setText("Usu??rio:");

        jLabel1.setText("R$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboTipoDespesa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labUsuario)
                            .addComponent(labTipo)
                            .addComponent(labVencimento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dataDataVenc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labDataPag)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labValorPago)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorPago)
                            .addComponent(dataDataPag, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labUsuario)
                    .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipoDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labVencimento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dataDataVenc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labDataPag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataDataPag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labValorPago)
                    .addComponent(txtValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        botSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-salvar-30.png"))); // NOI18N
        botSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botSalvarActionPerformed(evt);
            }
        });

        botEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-l??pis-24.png"))); // NOI18N
        botEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botEditarActionPerformed(evt);
            }
        });

        botExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/delete_remove_bin_icon-icons.com_72400.png"))); // NOI18N
        botExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botEditar)
                .addGap(18, 18, 18)
                .addComponent(botExcluir)
                .addGap(18, 18, 18)
                .addComponent(botSalvar)
                .addGap(21, 21, 21))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botEditar, botExcluir, botSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(botSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botEditar, botExcluir, botSalvar});

        getAccessibleContext().setAccessibleDescription("");

        bindingGroup.bind();

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
        despesa.setTipodespesaTpdespCod((TipoDespesa) comboTipoDespesa.getSelectedItem());
//        despesa.setDespCoddesp(Integer.parseInt().getDespCoddesp());
        despesa.setCaixaCxCodcaixa(CaixaDAO.getCaixa());
        despesa.setDespDescricao(comboTipoDespesa.getSelectedItem().toString());
        despesa.setDespDataPagamento(dataDataPag.getDate());
        despesa.setDespDataVencimento(dataDataVenc.getDate());
        
        if (dataDataPag.getDate() == null) {
            JOptionPane.showMessageDialog(this, "N??o preencheu o campo obrigat??rio: Data Pagamento.");
            return;
        }
        if (dataDataVenc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "N??o preencheu o campo obrigat??rio: Data Vencimento.");
            return;
        }
        if (dataDataVenc.getDate() == null) {
            despesa.setDespStatusPagamento("N");
        } else {
            despesa.setDespStatusPagamento("P");
        }
//        despesa.setDespValor(Float.parseFloat(txtValor.getText()));
        despesa.setDespVlPago(Float.parseFloat(txtValorPago.getText()));
        despesa.setUsuarioUsCodusuario((Usuario) comboUsuario.getSelectedItem());
        despesa.setDespStatus("A");

        System.out.println(despesa.getUsuarioUsCodusuario());
        controle.salvarNovo(despesa);
        dispose();
        JOptionPane.showMessageDialog(null, "Despesa Salva");


    }//GEN-LAST:event_botSalvarActionPerformed

    private void botExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botExcluirActionPerformed
        // TODO add your handling code here:
        liberarCampos(true);
        int resposta;

        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir?");
        if (resposta == JOptionPane.YES_OPTION) {

            controle.excluir(despesa);
            dispose();
        }
    }//GEN-LAST:event_botExcluirActionPerformed

    private void botEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botEditarActionPerformed
        // TODO add your handling code here:
        liberarCampos(true);
    }//GEN-LAST:event_botEditarActionPerformed

    public void preencheComboTipoDespesa() {
        listTp = new ArrayList<>();
        
        listTp = controleTipoDespesa.listarTodos();

        for (TipoDespesa tpd : listTp) {
            
            comboTipoDespesa.addItem(tpd);
        }
    }

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
            java.util.logging.Logger.getLogger(JDialogInserirDespesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogInserirDespesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogInserirDespesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogInserirDespesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager SistemaReiDoGeloPUEntityManager;
    private javax.swing.JButton botEditar;
    private javax.swing.JButton botExcluir;
    private javax.swing.JButton botSalvar;
    private javax.swing.JComboBox<TipoDespesa> comboTipoDespesa;
    private javax.swing.JComboBox<String> comboUsuario;
    private org.netbeans.modules.form.InvalidComponent dataDataPag;
    private org.netbeans.modules.form.InvalidComponent dataDataVenc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labDataPag;
    private javax.swing.JLabel labTipo;
    private javax.swing.JLabel labUsuario;
    private javax.swing.JLabel labValorPago;
    private javax.swing.JLabel labVencimento;
    private java.util.List<modelo.TipoDespesa> tipodespesaList;
    private javax.persistence.Query tipodespesaQuery;
    private javax.swing.JTextField txtValorPago;
    private java.util.List<modelo.Usuario> usuarioList;
    private javax.persistence.Query usuarioQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
