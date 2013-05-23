package pv168.project.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv168.project.Disk;
import pv168.project.GenreEnum;
import pv168.project.KindEnum;
import pv168.project.TypeEnum;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Roman Jakubik
 * Date: 10.5.13
 * Time: 15:14
 */
public class DisksTableModel extends AbstractTableModel{

    final static Logger log = LoggerFactory.getLogger(DisksTableModel.class);

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
                return KindEnum.class;
            case 5:
                return TypeEnum.class;
            case 6:
                return String.class;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    public void addDisk(Disk input) {
        disks.add(input);
        int lastRow = disks.size() - 1;

        fireTableRowsInserted(lastRow, lastRow);
    }

    public void editDisk(Disk oldDisk, Disk newDisk)
    {
        if(disks.remove(oldDisk))
        {
            disks.add(newDisk);
            fireTableDataChanged();
        }
    }

    public void removeAll()
    {
        this.disks.clear();
    }

    public List<Disk> getAll()
    {
        return disks;
    }

    public Disk getDiskAt(int index)
    {
        return disks.get(index);
    }

    public void removeAt(int[] indexes)
    {
        if(indexes.length > 0)
        {
            log.info("Books: " + disks.size());
            log.info("Selected: " + indexes.length);

            for(Integer i =indexes.length-1;i>=0;i--){
                disks.remove(indexes[i]);
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


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Disk disk = disks.get(rowIndex);
        switch (columnIndex) {
            case 0:
                disk.setAuthor((String) aValue);
                break;
            case 1:
                disk.setName((String) aValue);
                break;
            case 2:
                disk.setReleaseYear((Date) aValue);
                break;
            case 3:
                disk.setGenre((GenreEnum) aValue);
                break;
            case 4:
                disk.setKind((KindEnum) aValue);
                break;
            case 5:
                disk.setType((TypeEnum)aValue);
                break;
            case 6:
                disk.setPosition((String) aValue);
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
            case 6:
                return true;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

}