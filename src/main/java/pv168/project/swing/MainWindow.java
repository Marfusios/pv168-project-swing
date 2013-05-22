package pv168.project.swing;


import pv168.project.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: m4r10
 * Date: 5/7/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow {

    private JPanel topPanel;
    private JTabbedPane tabbedPane1;
    private JPanel tabBooks;
    private JButton bookCreateButton;
    private JTable tableDisks;
    private JTable tableBooks;
    private JButton bookDeleteButton;
    private JButton bookUpdateButton;
    private JPanel tabDisks;
    private JButton diskCreateButton;
    private JButton diskDeleteButton;
    private JButton diskUpdateButton;
    static MainWindow thisWindow;
    private DisksTableModel modelDisks;
    private BooksTableModel modelBooks;
    private boolean isSaveAble = false;
    public boolean isSaveAble() {
        return isSaveAble;
    }
    public void setSaveAble(boolean saveAble) {
        isSaveAble = saveAble;
    }

    private ManagerEntities managerEntities;
    public ManagerEntities getManagerEntities() {
        return managerEntities;
    }

    public DisksTableModel getModelDisks() {
        return modelDisks;
    }

    public BooksTableModel getModelBooks() {
        return modelBooks;
    }

    public MainWindow() {
        ManagerDB.startServer();
        ManagerDB.createDatabase();
        managerEntities = new ManagerEntitiesImpl();


       bookCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateDialog().setVisible(true);
            }
        });

        bookUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableBooks.getSelectedRow() >= 0)
                {
                    new CreateDialog(modelBooks.getBookAt(tableBooks.getSelectedRow())).setVisible(true);
                }
            }
        });

        bookDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableBooks.getSelectedRow() >= 0)
                {
                    modelBooks.removeAt(tableBooks.getSelectedRows());
                }
            }
        });



        setModelsToTables();
        setEditableCells();
    }

    private JMenuBar createMenu() {
        //hlavní úroveň menu
        ResourceBundle rb = ResourceBundle.getBundle("pv168.project.swing.Bundle");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(rb.getString("File"));
        final JMenu helpMenu = new JMenu(rb.getString("Help"));
        JMenu lafMenu = new JMenu(rb.getString("LaF"));
        menuBar.add(fileMenu);
        menuBar.add(lafMenu);
        menuBar.add(helpMenu);
        menuBar.add(Box.createHorizontalGlue());

        //menu File
        JMenuItem exitMenuItem = new JMenuItem(rb.getString("Exit"));
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        //menu Help
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(helpMenu,"Skvělá aplikace (c) Já","About",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            JMenuItem item = new JMenuItem(info.getName());
            lafMenu.add(item);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                        SwingUtilities.updateComponentTreeUI(MainWindow.this.topPanel);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return menuBar;
    }

    public static void main(String[] args) {
        final ResourceBundle rb = ResourceBundle.getBundle("pv168.project.swing.Bundle");
        thisWindow = new MainWindow();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame(rb.getString("Nazev"));
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setContentPane(thisWindow.topPanel);
                frame.setPreferredSize(new Dimension(800,600));
                frame.pack();
                frame.setVisible(true);
                frame.setJMenuBar(thisWindow.createMenu());
            }
        });

        loadAllDataFromDB();

        thisWindow.getModelBooks().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (thisWindow.isSaveAble())
                    saveAllDataToDB();
            }
        });
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    private void setModelsToTables()
    {
        modelDisks = new DisksTableModel();
        tableDisks.setModel(modelDisks);

        modelBooks = new BooksTableModel();
        tableBooks.setModel(modelBooks);
    }

    private void setEditableCells()
    {
        JComboBox genreComboBox = new JComboBox();
        for (GenreEnum f : GenreEnum.values()) {
            genreComboBox.addItem(f);
        }
        tableBooks.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(genreComboBox));
        tableDisks.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(genreComboBox));

        JComboBox kindComboBox = new JComboBox();
        for (KindEnum f : KindEnum.values()) {
            kindComboBox.addItem(f);
        }
        tableDisks.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(kindComboBox));

        JComboBox typeComboBox = new JComboBox();
        for (TypeEnum f : TypeEnum.values()) {
            typeComboBox.addItem(f);
        }
        tableDisks.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(typeComboBox));
    }

    public static void loadAllDataFromDB()
    {
        LoadFromDBSwingWorker swingWorker = new LoadFromDBSwingWorker();
        swingWorker.execute();
    }

    public static void saveAllDataToDB()
    {
        SaveToDBSwingWorker swingWorker = new SaveToDBSwingWorker();
        swingWorker.execute();
    }
}
