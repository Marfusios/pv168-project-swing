package pv168.project.swing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
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
    private JPanel tabDisks;
    private JButton createButton;
    private JButton smazatButton;
    private JButton upravitButton;
    private JTable tableDisks;
    private JTable tableBooks;

    public MainWindow() {
        /*button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("tlačítko zmáčknuto");
            }
        });  */
        tableDisks.setModel(new DisksTableModel());
        tableBooks.setModel(new BooksTableModel());
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     CreateDialog dialog= new CreateDialog();
                     dialog.pack();
                     dialog.setVisible(true);
            }
        });
    }
    private JMenuBar createMenu() {
        //hlavní úroveň menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        final JMenu helpMenu = new JMenu("Help");
        JMenu lafMenu = new JMenu("Look and feel");
        menuBar.add(fileMenu);
        menuBar.add(lafMenu);
        menuBar.add(helpMenu);
        menuBar.add(Box.createHorizontalGlue());
        //menu File
        JMenuItem exitMenuItem = new JMenuItem("Exit");
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
        final MainWindow  thisWindow = new MainWindow();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Home evidency");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setContentPane(thisWindow.topPanel);
                frame.setPreferredSize(new Dimension(800,600));
                frame.pack();
                frame.setVisible(true);
                frame.setJMenuBar(thisWindow.createMenu());
            }
        });
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
