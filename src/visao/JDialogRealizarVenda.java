/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ClienteDAO;
import controle.ProdutoDAO;
import controle.UsuarioDAO;
import controle.VendaDAO;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;

/**
 *
 * @author osmar
 */
public class JDialogRealizarVenda extends javax.swing.JDialog {

    /**
     * Creates new form JDialogRealizarVenda
     *
     */
    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm:ss");

    Date hoje;

    Cliente cliente = new Cliente();

    ClienteDAO contCli = new ClienteDAO();

    ProdutoDAO contProd = new ProdutoDAO();

    private List<Produto> listaProduto = new ArrayList<>();

    public List<ItemVenda> listaItemVenda = new ArrayList<>();

    private ArrayList<Venda> listaVenda = new ArrayList<>();
//    DecimalFormat fd = new DecimalFormat("#,###.00");

    private DefaultTableModel itemVenda;
    Produto prod = null;

    ItemVenda itv = new ItemVenda();

    ItemVenda iv;

    Venda venda = new Venda();

    Date dataVenda;

    JScrollPane sp = new JScrollPane();
    JComboBox cb = new JComboBox();


//     private DefaultTableModel itemVenda;
    public JDialogRealizarVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        hoje = new Date();
        preencheComboProduto();
        preencheComboCliente();

        txtData.setText(formatarData.format(hoje));
        formatadoDesconto.setValue(0);
//        formatadoDesconto.setText("R$");
        formatadoValorAPagar.setValue(0);
//        formatadoValorAPagar.setText("R$");
        formatadoValorTotal.setValue(0);
//        formatadoValorTotal.setText("R$");

//        itemVenda = (DefaultTableModel) tabItemVenda.getModel();
        jSpinnerQuantProd.requestFocus();
        
        comboCliente = cb;

        cb.setEditable(true);

        cb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //if(e.getStateChange() == ItemEvent.SELECTED  && e.getID() == KeyEvent.VK_BACK_SPACE) // para evitar duplicações
                //cb.getEditor().setItem( digitado);
            }
        });

        cb.getEditor().getEditorComponent().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char letra = e.getKeyChar();
                if (Character.isLetter(e.getKeyChar())) {
                    //e.setKeyChar(e.getKeyChar());
                    e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
                }

                /*if(Character.isDigit(e.getKeyChar())){
                    System.out.println("É digito");
                    e.setKeyChar(e.getKeyChar());
                }*/
                if (!Character.isLetter(e.getKeyChar())) {
                    e.setKeyChar('\0');
                }
                if (letra == ' ') {
                    e.setKeyChar(letra);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                } else {
                    String digitado = cb.getEditor().getItem().toString();
                    int tam = digitado.length();

                    int itemCount = cb.getItemCount();

                    for (int i = 0; i < itemCount; i++) {
                        cb.removeItemAt(0);//remove todos os itens da lista de sugestões do combo
                    }
                    for (int i = 0; i < listCliente.size(); i++) {
                        if (listCliente.get(i) == null || listCliente.get(i).equals("")) {
                        } else {
                            try {
                                if ((digitado.equals(listCliente.get(i).toString().substring(0, tam))) && !digitado.equals("")) {
                                    cb.addItem(listCliente.get(i).toString());
                                }
                            } catch (Exception er) {

                            }
                        }
                    }
                    int count = cb.getItemCount();
                    if (count > 0) {
                        cb.setPopupVisible(false);
                        try {
                            cb.setPopupVisible(true);
                        } catch (Exception erro) {

                        }
                    } else {
                        cb.setPopupVisible(false);
                    }
                    cb.getEditor().setItem(digitado);
                }
            }
        });
        ouvinte();
    }
    //Atualizar Tabela

    public void atualizarTabela() {
        limparTabela();
        inserirTabela();
    }
    private String nomePesq = "";

    //Limpar Tabela
    public void limparTabela() {
        //Pegar modelo
        DefaultTableModel modelo = (DefaultTableModel) tabIemVenda.getModel();
        //Remove linha enquanto haver linha
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    //Inserir Tabela
    public void inserirTabela() {
        //Pegar modelo
        DecimalFormat fd = new DecimalFormat("#,###.00");
        DefaultTableModel modelo = (DefaultTableModel) tabIemVenda.getModel();
        //Pega todos item venda do List
        for (ItemVenda it : listaItemVenda) {
            Object obj[] = {it.getProduto().getProdDescricao(), it.getItvQtd(), "R$" + fd.format(it.getItvValorUni()), "R$" + fd.format(it.getItvQtd() * it.getItvValorUni())};
            modelo.addRow(obj);
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

        entityManager1 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SistemaReiDoGeloPU").createEntityManager();
        query2 = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT p FROM Produto p where  p.prodStatus = 'D'");
        list1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : query2.getResultList();
        query1 = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT c FROM Cliente c");
        listCliente = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : query1.getResultList();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        labDesconto = new javax.swing.JLabel();
        labFormaPagamento = new javax.swing.JLabel();
        labCliente = new javax.swing.JLabel();
        labValorTotal = new javax.swing.JLabel();
        labData = new javax.swing.JLabel();
        comboFormaPag = new javax.swing.JComboBox<>();
        labValorPagar = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox<>();
        formatadoDesconto = new javax.swing.JFormattedTextField();
        botAplicarDesconto = new javax.swing.JButton();
        txtData = new javax.swing.JTextField();
        formatadoValorTotal = new javax.swing.JFormattedTextField();
        formatadoValorAPagar = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        comboItemVenda = new javax.swing.JComboBox<>();
        botConfirmar = new javax.swing.JButton();
        labQuantidade = new javax.swing.JLabel();
        jSpinnerQuantProd = new javax.swing.JSpinner();
        botAdicionarItem = new javax.swing.JButton();
        botExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabIemVenda = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Venda", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24))); // NOI18N

        labDesconto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labDesconto.setText("Desconto:");

        labFormaPagamento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labFormaPagamento.setText("Forma de Pagamento:");

        labCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labCliente.setText("Cliente:");

        labValorTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labValorTotal.setText("Valor Total:");

        labData.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labData.setText("Data:");

        comboFormaPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Á vista", "A prazo" }));

        labValorPagar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labValorPagar.setText("Valor a Pagar:");

        comboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClienteActionPerformed(evt);
            }
        });
        comboCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboClienteKeyPressed(evt);
            }
        });

        formatadoDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        botAplicarDesconto.setText("Aplicar Desc.");
        botAplicarDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAplicarDescontoActionPerformed(evt);
            }
        });

        txtData.setEditable(false);

        formatadoValorTotal.setEditable(false);
        formatadoValorTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        formatadoValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatadoValorTotalActionPerformed(evt);
            }
        });

        formatadoValorAPagar.setEditable(false);
        formatadoValorAPagar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel1.setText("R$");

        jLabel2.setText("R$");

        jLabel3.setText("R$");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formatadoValorTotal))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labCliente)
                        .addGap(18, 18, 18)
                        .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(labValorPagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formatadoValorAPagar)))
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(labDesconto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(formatadoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botAplicarDesconto)
                        .addGap(157, 157, 157))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(labFormaPagamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(labData)
                                .addGap(30, 30, 30)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labCliente)
                            .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labData)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labValorTotal)
                            .addComponent(formatadoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(19, 19, 19)
                        .addComponent(labValorPagar))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botAplicarDesconto)
                            .addComponent(formatadoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labDesconto)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labFormaPagamento)
                            .addComponent(formatadoValorAPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item da Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        comboItemVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboItemVendaActionPerformed(evt);
            }
        });

        botConfirmar.setBackground(new java.awt.Color(255, 255, 255));
        botConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/confirmar.png"))); // NOI18N
        botConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botConfirmarActionPerformed(evt);
            }
        });

        labQuantidade.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labQuantidade.setText("Quantidade:");

        jSpinnerQuantProd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        botAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/mais32.png"))); // NOI18N
        botAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAdicionarItemActionPerformed(evt);
            }
        });

        botExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8-fechar-janela-24.png"))); // NOI18N
        botExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botExcluirActionPerformed(evt);
            }
        });

        tabIemVenda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabIemVenda.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabIemVenda);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botConfirmar)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(comboItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addComponent(labQuantidade)
                            .addGap(18, 18, 18)
                            .addComponent(jSpinnerQuantProd, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(botAdicionarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(botAdicionarItem)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labQuantidade)
                            .addComponent(jSpinnerQuantProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botConfirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 795, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(823, 618));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
     public void calcularValorAPagar() {
        float total = 0;
        total = ((Number) formatadoValorTotal.getValue()).floatValue() - ((Number) formatadoDesconto.getValue()).floatValue();
        formatadoValorAPagar.setValue(total);
    }
    private void botExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botExcluirActionPerformed
        // TODO add your handling code here:
        int totalquant = 0;
        if (tabIemVenda.getSelectedRow() != -1) {

            listaItemVenda.remove(tabIemVenda.getSelectedRow());
            atualizarTabela();
            calcularValores();
        }
    }//GEN-LAST:event_botExcluirActionPerformed

    void remover() {
        int linha = tabIemVenda.getSelectedRow();

        for (int i = 0; i <= listaItemVenda.size(); i++) {
            if (i == linha) {
                iv.setItvQtd(-1);
            }

        }
    }
    private void botAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAdicionarItemActionPerformed
        // TODO add your handling code here:
        if (comboItemVenda.getSelectedIndex() != -1 && (Integer) jSpinnerQuantProd.getValue() > 0) {
            Produto prod = listaProduto.get(comboItemVenda.getSelectedIndex());
            ItemVenda iv = new ItemVenda();

            iv.setProduto(prod);
            iv.setItvQtd((Integer) jSpinnerQuantProd.getValue());
            iv.setItvValorUni(prod.getProdPreco());

            boolean adicionar = true;

            for (ItemVenda fiv : listaItemVenda) {
                if (fiv.getProduto().equals(iv.getProduto())) {
                    adicionar = false;
                    fiv.setItvQtd(fiv.getItvQtd() + iv.getItvQtd());
                }
            }
            if (adicionar) {
                listaItemVenda.add(iv);
            }
            atualizarTabela();
            calcularValores();
        }
    }//GEN-LAST:event_botAdicionarItemActionPerformed

    private void botConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botConfirmarActionPerformed
        // TODO add your handling code here:
        if (((Number) formatadoValorAPagar.getValue()).floatValue() > 0) {
            Venda venda = new Venda(new Date(), comboFormaPag.getSelectedItem().toString(),
                    ((Number) formatadoValorAPagar.getValue()).floatValue(), cliente, UsuarioDAO.isUsuarioLogado(),
                    listaItemVenda);
            venda.setVndStatus("A");
            venda.setVndStatusPagamento("N");
            VendaDAO ctrlVenda = new VendaDAO();
            ctrlVenda.salvarNovo(venda);
            JOptionPane.showMessageDialog(this, "Venda realizada.");

            dispose();
        }
    }//GEN-LAST:event_botConfirmarActionPerformed

    private void comboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClienteActionPerformed
        // TODO add your handling code here:
        List<Cliente> listCli = new ArrayList<>();

        String clienteSelecionado = (String) comboCliente.getSelectedItem();
        listCli = contCli.consultarPorNome(clienteSelecionado, "A");

        for (Cliente cliList : listCli) {
            if (cliList.getCliNome().equals(clienteSelecionado)) {
                this.cliente = cliList;
            }
        }

        System.out.println("Cliente global" + cliente + cliente.getCliCodcli());
    }//GEN-LAST:event_comboClienteActionPerformed

    private void formatadoValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatadoValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formatadoValorTotalActionPerformed

    private void botAplicarDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAplicarDescontoActionPerformed
        // TODO add your handling code here:
        calcularValores();

    }//GEN-LAST:event_botAplicarDescontoActionPerformed

    private void comboClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboClienteKeyPressed
        // TODO add your handling code here:
//        

        if (evt.getKeyCode() == 8) {
            String n = nomePesq;
            if (nomePesq.length() >= 1) {
                nomePesq = n.substring(0, n.length() - 1);
            }

        } else {
            nomePesq += evt.getKeyChar();
        }
        System.out.println(nomePesq);
//          
        List<Cliente> listaCliente;

        listaCliente = contCli.consultarPorNome(nomePesq, "A");
        comboCliente.removeAllItems();
        for (Cliente c : listaCliente) {
            System.out.println(c.toString());
//              comboCliente.addItem(c.getCliNome());
        }

    }//GEN-LAST:event_comboClienteKeyPressed

    private void comboItemVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboItemVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboItemVendaActionPerformed

    public void calcularValores() {
        calcularValorTotal();
        calcularValorAPagar();
    }

    public void calcularValorTotal() {
        float total = 0;
        for (ItemVenda iv : listaItemVenda) {
            total = total + iv.getItvValorUni() * iv.getItvQtd();
        }
        formatadoValorTotal.setValue(total);
    }

    public void preencheComboProduto() {
        listaProduto = new ArrayList<>();

        listaProduto = contProd.listarTodos();

        for (Produto prod : listaProduto) {
            comboItemVenda.addItem(prod.getProdDescricao() + "");
        }
    }

    public void preencheComboCliente() {
        List<Cliente> listaCliente = new ArrayList<>();

        listaCliente = contCli.listarTodos();
        if (!listaCliente.isEmpty()) {
            for (Cliente cli : listaCliente) {

//                if (cli.getCliStatus().equals("A")) {
                comboCliente.addItem(cli.getCliNome() + "");
//                }
            }

        }

        System.out.println("List CLi" + listaCliente);
        if (comboCliente.getSelectedIndex() >= 0) {
//            Cliente clienteSelecionado = (Cliente) comboCliente5.getSelectedItem();
            Cliente cli = new Cliente();
            this.listCliente.add(cli);

            if (listaCliente.isEmpty()) {
                System.out.println("Não encontrado.");
//                labMostrarCliente.setText("Cliente não localizado.");
            } else {
                cliente = listaCliente.get(0);
                comboCliente.setSelectedItem(cliente.getCliNome());

            }

        }
    }

//    private void atualizaTabela(ArrayList<ItemVenda> listProduto) {
//
//       
//        
//        for (ItemVenda i : listProduto) {
//            float x = i.getItvValorUni() * i.getItvQtd();
//            String v = floatString(x);
//            itemVenda.addRow(new Object[]{i.getProdCodprod(), "R$" + floatString(i.getItvValorUni()), i.getItvQtd(), "R$" + v});
//        }
//    }
//    private float VerificaValor() {
////        float total = 0;
////        for (ItemVenda iv : listProduto) {
////            total = total + iv.getItvQtd() * iv.getItvValorUni();
////
////        }
////        return total;
//    }
    public void preencherCampos() {
        comboCliente.setSelectedItem(cliente.getCliNome());
        txtData.setText(venda.getVndData().toString());
        comboFormaPag.setSelectedItem(venda.getVndFormapgto().toString());
        formatadoValorTotal.setValue(venda.getVndValor());
        atualizarTabela();
    }

    public String floatString(float f) {
        Locale.setDefault(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        String s = df.format(f);
        return s;
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
            java.util.logging.Logger.getLogger(JDialogRealizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogRealizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogRealizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogRealizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogRealizarVenda dialog = new JDialogRealizarVenda(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void ouvinte() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == cb.getEditor().getEditorComponent()) {
                    try {//System.out.println("teste 37 "+digitado);
                        cb.setSelectedIndex(0);
                        //cb.getEditor().getItem();
                        //jt.removeEditor();//Use este para remover o combobox da célula da tabela
                    } catch (Exception er) {
                    }
                }
                return false;
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAdicionarItem;
    private javax.swing.JButton botAplicarDesconto;
    private javax.swing.JButton botConfirmar;
    private javax.swing.JButton botExcluir;
    private javax.swing.JComboBox<String> comboCliente;
    private javax.swing.JComboBox<String> comboFormaPag;
    private javax.swing.JComboBox<String> comboItemVenda;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JFormattedTextField formatadoDesconto;
    private javax.swing.JFormattedTextField formatadoValorAPagar;
    private javax.swing.JFormattedTextField formatadoValorTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerQuantProd;
    private javax.swing.JLabel labCliente;
    private javax.swing.JLabel labData;
    private javax.swing.JLabel labDesconto;
    private javax.swing.JLabel labFormaPagamento;
    private javax.swing.JLabel labQuantidade;
    private javax.swing.JLabel labValorPagar;
    private javax.swing.JLabel labValorTotal;
    private java.util.List list1;
    private java.util.List<modelo.Cliente> listCliente;
    private javax.persistence.Query query1;
    private javax.persistence.Query query2;
    private javax.swing.JTable tabIemVenda;
    private javax.swing.JTextField txtData;
    // End of variables declaration//GEN-END:variables

}
