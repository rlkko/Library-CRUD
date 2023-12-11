/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import dto.AuthorBook;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import service.BookService;

/**
 *
 * @author ricar
 */
public class AuthorBookTableModel extends AbstractTableModel {

    private List<String> colunas = new ArrayList<>();
    private List<AuthorBook> dados;

    public AuthorBookTableModel() {
        this.colunas.add("ISBN");
        this.colunas.add("Title");
        this.colunas.add("Authors");
        this.colunas.add("Quantity");

        updateList();
    }

    @Override
    public String getColumnName(int column) {
        return colunas.get(column);
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return !(col == 2 || col == 0);
    }

    public void updateList() {
        dados = BookService.retrieveAllBooks();
    }

    public void searchByAuthor(String author) {
        dados.clear();
        dados = BookService.retrieveBookByAuthor(author);
    }

    public boolean deleteBook(int row) {
        dados.remove(row);
        return BookService.deleteBook(dados.get(row).getISBN());
    }

    public void searchByTitle(String title) {
        dados.clear();
        dados = BookService.retrieveBookByTitle(title);

    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        dados.get(row).setTitulo((String) aValue);
        fireTableCellUpdated(row, col);

        switch (col) {
            case 1 ->
                BookService.updateBookTitle((String) aValue,
                        dados.get(row).getISBN());
            case 3 ->
                BookService.updateBookQuantity(Integer.parseInt((String) aValue),
                        dados.get(row).getISBN());
        }

        updateList();
    }

    @Override
    public Object getValueAt(int linha, int coluna
    ) {
        switch (coluna) {
            case 0 -> {
                return dados.get(linha).getISBN();
            }
            case 1 -> {
                return dados.get(linha).getTitulo();
            }
            case 2 -> {
                return dados.get(linha).getNome_autor();
            }
            case 3 -> {
                return dados.get(linha).getNumero_copias();
            }
        }
        return null;
    }

}