/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import bookstore.dto.Author;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import bookstore.services.AuthorService;

/**
 *
 * @author ricar
 */
public class AuthorPickerTableModel extends AbstractTableModel {

    List<String> header = null;
    List<Author> data = null;

    public AuthorPickerTableModel() {
        header = new ArrayList<>(Arrays.asList(
                "id",
                "name")
        );

        data = AuthorService.retrieveAuthorList();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.size();
    }

    @Override
    public String getColumnName(int column) {
        return header.get(column);
    }

    public Author getAuthor(int row) {
        return data.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return data.get(rowIndex).number();
            }
            case 1 -> {
                return data.get(rowIndex).name();
            }
        }
        return null;
    }
}
