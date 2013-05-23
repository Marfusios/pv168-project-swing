package pv168.project.swing;

import pv168.project.Disk;
import pv168.project.GenreEnum;
import pv168.project.KindEnum;
import pv168.project.TypeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class DiskDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfAuthor;
    private JTextField tfName;
    private JTextField tfPosition;
    private JTextField tfYear;
    private JComboBox cbGenre;
    private JComboBox cbType;
    private JComboBox cbKInd;

    private Disk inputDisk = null;

    public DiskDialog() {
        setContentPane(contentPane);
        setModal(true);
        setPreferredSize(new Dimension(400, 250));
        MainWindow.centreWindow(this);
        pack();
        getRootPane().setDefaultButton(buttonOK);

        cbGenre.setModel(new DefaultComboBoxModel(GenreEnum.values()));
        cbKInd.setModel(new DefaultComboBoxModel(KindEnum.values()));
        cbType.setModel(new DefaultComboBoxModel(TypeEnum.values()));
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

    public DiskDialog(Disk input)
    {
        this();

        inputDisk = input;

        tfAuthor.setText(inputDisk.getAuthor());
        tfName.setText(inputDisk.getName());
        tfPosition.setText(inputDisk.getPosition());

        if(inputDisk.getReleaseYear() != null){
            tfYear.setText(inputDisk.getReleaseYear().toString());
        }

        if(inputDisk.getKind() != null){
            cbKInd.setSelectedItem(inputDisk.getKind());
        }

        if(inputDisk.getType() != null){
            cbType.setSelectedItem(inputDisk.getType());
        }

        if(inputDisk.getGenre() != null)
        {
            cbGenre.setSelectedItem(inputDisk.getGenre());
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


        if(("").equals(tfName.getText()))
        {
            tfName.setBackground(Color.red);
            return;
        }
        else
        {
            tfName.setBackground(Color.white);
        }


        Disk tmp = new Disk(tfName.getText(), tfAuthor.getText());
        tmp.setGenre((GenreEnum)cbGenre.getSelectedItem());

        tmp.setKind((KindEnum)cbKInd.getSelectedItem());
        tmp.setType((TypeEnum)cbType.getSelectedItem());

        tmp.setPosition(tfPosition.getText());

        if(inputDisk == null)
        {
            MainWindow.thisWindow.getModelDisks().addDisk(tmp);
        }
        else
        {
            MainWindow.thisWindow.getModelDisks().editDisk(inputDisk, tmp);
        }


        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
