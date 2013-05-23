package pv168.project.swing;

import pv168.project.Book;
import pv168.project.GenreEnum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfTittle;
    private JTextField tfAuthor;
    private JTextField tfReleaseYear;
    private JComboBox cbGenre;
    private JTextField tfPosition;
    private JTextField tfPage;

    private Book inputBook = null;

    public CreateDialog() {
        setContentPane(contentPane);
        setModal(true);
        setPreferredSize(new Dimension(300, 250));
        pack();
        getRootPane().setDefaultButton(buttonOK);

        cbGenre.setModel(new DefaultComboBoxModel(GenreEnum.values()));
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public CreateDialog(Book input)
    {
        this();

        inputBook = input;

        tfAuthor.setText(inputBook.getAuthor());
        tfTittle.setText(inputBook.getName());
        tfPage.setText(Integer.toString(inputBook.getPageCount()));
        tfPosition.setText(inputBook.getPosition());

        if(inputBook.getReleaseYear() != null)
        {
            tfReleaseYear.setText(inputBook.getReleaseYear().toString());
        }


        if(inputBook.getGenre() != null)
        {
            cbGenre.setSelectedItem(inputBook.getGenre());
        }
    }

    private void onOK() {

        if(("").equals(tfAuthor.getText()))
        {
            tfAuthor.setBackground(Color.red);
            return;
        }
        else
        {
            tfAuthor.setBackground(Color.white);
        }


        if(("").equals(tfTittle.getText()))
        {
            tfTittle.setBackground(Color.red);
            return;
        }
        else
        {
            tfTittle.setBackground(Color.white);
        }


        Book tmp = new Book(tfTittle.getText(), tfAuthor.getText());
        tmp.setGenre((GenreEnum)cbGenre.getSelectedItem());

        try {
        int pg = Integer.parseInt(tfPage.getText());
        tmp.setPageCount(pg);
        }
        catch(Exception ex)
        {
            tfPage.setBackground(Color.red);
            return;
        }
        tfPage.setBackground(Color.white);

        tmp.setPosition(tfPosition.getText());

        if(inputBook == null)
        {
            MainWindow.thisWindow.getModelBooks().addBook(tmp);
        }
        else
        {
            MainWindow.thisWindow.getModelBooks().editBook(inputBook, tmp);
        }


        dispose();
    }

    private void onCancel() {
        dispose();
    }


}
