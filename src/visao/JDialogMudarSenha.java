/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.UsuarioDAO;
import java.awt.Frame;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author osmar
 */
public class JDialogMudarSenha extends javax.swing.JDialog {

    private Usuario usuario = new Usuario();
    private UsuarioDAO controle = new UsuarioDAO();
    private TelaLogin telaLogin = new TelaLogin();

//    UsuarioDAO ud = new UsuarioDAO();
//    Usuario usuLogado = null;
    /**
     * Creates new form JDialogMudarSenha
     */
    public JDialogMudarSenha(Frame frame, boolean bln) {
        super(frame, bln);
        f = frame;
        initComponents();

//        passwordNovaSenha.setEnabled(false);
//        passwordConfirmaNovaSenha.setEnabled(false);
    }
    private Usuario usu = null;

    Frame f = new Frame();
    
    

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;

    }
    {System.out.println("usuario"+usu); }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botConfirmar = new javax.swing.JButton();
        labSenhaAtual = new javax.swing.JLabel();
        labNovaSenha = new javax.swing.JLabel();
        labConfirmarSenha = new javax.swing.JLabel();
        passwordSenhaAtual = new javax.swing.JPasswordField();
        passwordNovaSenha = new javax.swing.JPasswordField();
        passwordConfirmaNovaSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        botConfirmar.setText("Confirmar");
        botConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botConfirmarActionPerformed(evt);
            }
        });

        labSenhaAtual.setText("Senha atual:");

        labNovaSenha.setText("Nova senha:");

        labConfirmarSenha.setText("Confirmar nova senha:");

        passwordSenhaAtual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordSenhaAtualFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labSenhaAtual, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labNovaSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labConfirmarSenha, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botConfirmar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(passwordSenhaAtual)
                        .addComponent(passwordNovaSenha)
                        .addComponent(passwordConfirmaNovaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labSenhaAtual)
                    .addComponent(passwordSenhaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNovaSenha)
                    .addComponent(passwordNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordConfirmaNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labConfirmarSenha))
                .addGap(18, 18, 18)
                .addComponent(botConfirmar)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void botConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botConfirmarActionPerformed

        String senha = passwordSenhaAtual.getText();

        Usuario us = new Usuario();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            try {
                byte password[] = md.digest(senha.getBytes("UTF-8"));

                StringBuilder hexString = new StringBuilder();
                for (byte b : password) {
                    hexString.append(String.format("%02X", 0xFF & b));
                }
                String senhahex = hexString.toString();
                if (senhahex.equals(usu.getUsSenha())) {
                    System.out.println("senhas iguais");
//                    JOptionPane.showMessageDialog(this, "Senhas iguais");
//                    passwordNovaSenha.setEnabled(true);
//                    passwordConfirmaNovaSenha.setEnabled(true);
                    if (passwordNovaSenha.getText().equals(passwordConfirmaNovaSenha.getText())) {

                        String senhaAlterada = passwordConfirmaNovaSenha.getText();

                        byte novaSenha[] = md.digest(senhaAlterada.getBytes("UTF-8"));

                        StringBuilder hexNovaSenha = new StringBuilder();
                        for (byte b : novaSenha) {
                            hexNovaSenha.append(String.format("%02X", 0xFF & b));
                        }
                        String senhaNovohex = hexNovaSenha.toString();

                        usu.setUsSenha(senhaNovohex);
                        dispose();
                        TelaLogin tela = new TelaLogin(null, true);
                       
                        tela.setVisible(true);
                        JOptionPane.showMessageDialog(this, "Senhas alterada.");
//                        telaLogin.
                        f.dispose();

                        dispose();
                        System.out.println("SEnha " + usu);
                        try {
                            System.out.println("Us " + usu);
                            controle.salvarNovo(usu);
                        } catch (Exception ex) {
                            Logger.getLogger(JDialogMudarSenha.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Senhas diferentes");

                    }

                } else {
                    System.out.println("senhas diferentes");

                }

                System.out.println(senhahex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(JDialogMudarSenha.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JDialogMudarSenha.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botConfirmarActionPerformed

    private void passwordSenhaAtualFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordSenhaAtualFocusLost
        // TODO add your handling code here:
        String senha = passwordSenhaAtual.getText();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            try {
                byte password[] = md.digest(senha.getBytes("UTF-8"));

                StringBuilder hexString = new StringBuilder();
                for (byte b : password) {
                    hexString.append(String.format("%02X", 0xFF & b));
                }
                String senhahex = hexString.toString();
                if (senhahex.equals(usu.getUsSenha())) {
                    System.out.println("senhas iguais");
//                    JOptionPane.showMessageDialog(this, "Senha atual correta.");
//                    passwordNovaSenha.setEnabled(true);
//                    passwordConfirmaNovaSenha.setEnabled(true);

                } else {
                    System.out.println("senhas diferentes");
                    if (this.isShowing()) {
                    JOptionPane.showMessageDialog(this, "Senhas atual incorreta.");
                    passwordSenhaAtual.setText("");
                    passwordSenhaAtual.requestFocus();
                    }

                    

//                    dispose();
//                    TelaLogin tela = new TelaLogin(null, true);
//                    tela.setVisible(true);
                }

                System.out.println(senhahex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(JDialogMudarSenha.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JDialogMudarSenha.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_passwordSenhaAtualFocusLost

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
            java.util.logging.Logger.getLogger(JDialogMudarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogMudarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogMudarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogMudarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new TelaPrincipalGerente().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(JDialogMudarSenha.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botConfirmar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labConfirmarSenha;
    private javax.swing.JLabel labNovaSenha;
    private javax.swing.JLabel labSenhaAtual;
    private javax.swing.JPasswordField passwordConfirmaNovaSenha;
    private javax.swing.JPasswordField passwordNovaSenha;
    private javax.swing.JPasswordField passwordSenhaAtual;
    // End of variables declaration//GEN-END:variables
}
