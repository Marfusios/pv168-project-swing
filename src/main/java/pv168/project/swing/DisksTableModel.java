package pv168.project.swing;

import pv168.project.Disk;
import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Roman Jakubik
 * Date: 10.5.13
 * Time: 15:14
 */
public class DisksTableModel extends AbstractTableModel{

    private List<Disk> disks = new ArrayList<Disk>();

    @Override
    public int getRowCount() {
        return disks.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
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
                return rb.getString("my.Kind");
            case 5:
                return rb.getString("my.Type");
            case 6:
                return rb.getString("my.Position");
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Disk disk = disks.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return disk.getAuthor();
            case 1:
                return disk.getName();
            case 2:
                return disk.getReleaseYear();
            case 3:
                return disk.getGenre();
            case 4:
                return disk.getKind();
            case 5:
                return disk.getType();
            case 6:
                return disk.getPosition();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

}