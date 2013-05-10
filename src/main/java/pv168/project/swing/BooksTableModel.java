package pv168.project.swing;

import pv168.project.Book;
import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Roman Jakubik
 * Date: 10.5.13
 * Time: 15:14
 */
public class BooksTableModel extends AbstractTableModel{

    private List<Book> books = new ArrayList<Book>();

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {

        ResourceBundle rb = ResourceBundle.getBundle("pv168.project.swing.Bundle");
        switch (columnIndex) {
            case 0:
                return rb.getString("my.Author");
            case 1:
                return rb.getString("my.Title");
            case 2:
                return rb.getString("my.ReleaseYear");
            case 3:
                return rb.getString("my.Genre");
            case 4:
                return rb.getString("my.PageCount");
            case 5:
                return rb.getString("my.Position");
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return book.getAuthor();
            case 1:
                return book.getName();
            case 2:
                return book.getReleaseYear();
            case 3:
                return book.getGenre();
            case 4:
                return book.getPageCount();
            case 5:
                return book.getPosition();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

}
