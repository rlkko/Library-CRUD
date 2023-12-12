/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import bookstore.dto.AuthorBook;
import bookstore.services.AuthorService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import bookstore.services.BookService;

/**
 *
 * @author ricar
 */
public class AuthorBookTableModel extends AbstractTableModel {

    private List<String> colunas = null;
    private List<AuthorBook> dados = null;

    public AuthorBookTableModel() {
        colunas = new ArrayList<>(Arrays.asList(
                "ISBN",
                "Title",
                "Authors",
                "Quantity")
        );

        dados = BookService.retrieveAllBooks();
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
    public void setValueAt(Object aValue, int row, int col) {
        dados.get(row).setTitle((String) aValue);
        fireTableCellUpdated(row, col);

        switch (col) {
            case 1 -> {
                BookService.updateBookTitle((String) aValue,
                        dados.get(row).getISBN());
                dados.get(row).setTitle((String) aValue);
            }

            case 3 -> {
                Integer new_value = Integer.valueOf((String) aValue);
                BookService.updateBookQuantity(new_value,
                        dados.get(row).getISBN());
                dados.get(row).setNumber_Copies(new_value);
            }
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna
    ) {
        switch (coluna) {
            case 0 -> {
                return dados.get(linha).getISBN();
            }
            case 1 -> {
                return dados.get(linha).getTitle();
            }
            case 2 -> {
                return dados.get(linha).getNome_autor();
            }
            case 3 -> {
                return dados.get(linha).Number_Copies();
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return !(col == 2 || col == 0);
    }

    @Override
    public void fireTableChanged(TableModelEvent e) {
        dados = BookService.retrieveAllBooks();
    }

    public void resetFilter() {
        dados = BookService.retrieveAllBooks();
    }

    public void searchByAuthor(String author) {
        dados.clear();
        dados = BookService.retrieveBookByAuthor(author);
    }

    public boolean addNewBook(String ISBN, String title,
            Integer quantity_available, Integer author_id) {

        String nome_autor = AuthorService.getAuthorName(author_id);

        dados.add(new AuthorBook(ISBN, nome_autor, title,
                quantity_available));

        return BookService.addNewBook(ISBN, title, quantity_available, author_id);
    }

    public void deleteBook(int row) {
        BookService.deleteBook(dados.get(row).getISBN());
        dados.remove(row);
    }

    public void searchByTitle(String title) {
        dados.clear();
        dados = BookService.retrieveBookByTitle(title);

    }

}
