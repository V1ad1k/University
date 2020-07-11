package Task5;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GetValues extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nValue;
    private JTextField pValue;
    private JTextField rValue;
    private JTextField zValue;
    private JLabel howManyLabel;
    private JTextField howManyValue;
    private JLabel nLabel;
    private JLabel pLabel;
    private JLabel rLabel;
    private JLabel zLabel;
    private JLabel tickLabel;
    private JTextField tickValue;
    private JLabel maxLoadLabel;
    private JTextField maxLoadValue;
    private int n;
    private int p;
    private int r;
    private int z;
    private int howMany;
    private int ticks;
    private int maxLoad;

    public GetValues() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        n = Integer.parseInt(nValue.getText());
        p = Integer.parseInt(pValue.getText());
        r = Integer.parseInt(rValue.getText());
        z = Integer.parseInt(zValue.getText());
        howMany = Integer.parseInt(howManyValue.getText());
        ticks = Integer.parseInt(tickValue.getText());
        maxLoad = Integer.parseInt(maxLoadValue.getText());
        dispose();
    }

    public int getN() {
        return n;
    }

    public int getP() {
        return p;
    }

    public int getR() {
        return r;
    }

    public int getZ() {
        return z;
    }

    public int getHowMany() {
        return howMany;
    }

    public int getTicks() {
        return ticks;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }
}
