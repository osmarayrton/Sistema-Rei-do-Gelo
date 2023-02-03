/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import modelo.Produto;

/**
 *
 * @author osmar
 */
public class TableModel extends AbstractTableModel {

    private List<Produto> dados = new ArrayList<>();
    private String[] colunas = {"Descrição", "Preço"};

    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        DecimalFormat fd = new DecimalFormat("#,###.00");

        switch (coluna) {
            case 0:
                return dados.get(linha).getProdDescricao();
            case 1:
                return "R$ " + fd.format(dados.get(linha).getProdPreco());
        }
        return null;
    }

    public void addRow(Produto p) {
        this.dados.add(p);
        this.fireTableDataChanged();
    }

    public void limpar() {
        this.dados.clear();
        this.fireTableDataChanged();
    }

}
