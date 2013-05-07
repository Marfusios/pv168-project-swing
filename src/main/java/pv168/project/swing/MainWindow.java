package pv168.project.swing;


import javax.swing.*;
import java.awt.*;

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
    private JButton deleteButton;
    private JButton editButton;
    private JTable table2;
    private JTable table1;

    public MainWindow() {
        /*button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("tlačítko zmáčknuto");
            }
        });  */
    }










    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Home evidency");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setContentPane(new MainWindow().topPanel);
                frame.setPreferredSize(new Dimension(800,600));
                frame.pack();
                frame.setVisible(true);
            }
        });
    }


}
