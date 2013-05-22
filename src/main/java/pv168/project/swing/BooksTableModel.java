package pv168.project.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv168.project.Book;
import pv168.project.GenreEnum;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Roman Jakubik
 * Date: 10.5.13
 * Time: 15:14
 */
public class BooksTableModel extends AbstractTableModel{

    final static Logger log = LoggerFactory.getLogger(BooksTableModel.class);

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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
                return String.class;
            case 2:
                return Date.class;
            case 3:
                return GenreEnum.class;
            case 4:
                return Integer.class;
            case 5:
                return String.class;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    public void addBook(Book input) {
        books.add(input);
        int lastRow = books.size() - 1;

        fireTableRowsInserted(lastRow, lastRow);
    }

    public void removeAll()
    {
        this.books.clear();
    }

    public List<Book> getAll()
    {
        return this.books;
    }

    public void removeAt(int index)
    {
        this.books.remove(index);

        fireTableRowsDeleted(index, index);
    }

    public void removeAt(int[] indexes)
    {
        if(indexes.length > 0)
        {
            log.info("Books: " + books.size());
            log.info("Selected: " + indexes.length);

           for(int i : indexes)
           {
               // TODO problem with deleting last item, fix it
               books.remove(i);
           }

           int firstRow, lastRow;
           if(indexes.length == 1)
               firstRow = lastRow = indexes[0];
           else
           {
               firstRow = indexes[0];
               lastRow = indexes[indexes.length-1];
           }

           fireTableRowsDeleted(firstRow, lastRow);
        }
    }

    public void editBook(Book oldBook, Book newBook)
    {
       if(books.remove(oldBook))
       {
           books.add(newBook);
           fireTableDataChanged();
       }
    }

    public Book getBookAt(int index)
    {
        return books.get(index);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                book.setAuthor((String) aValue);
                break;
            case 1:
                book.setName((String) aValue);
                break;
            case 2:
                book.setReleaseYear((Date) aValue);
                break;
            case 3:
                book.setGenre((GenreEnum) aValue);
                break;
            case 4:
                book.setPageCount((Integer) aValue);
                break;
            case 5:
                book.setPosition((String) aValue);
                break;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

}
