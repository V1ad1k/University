package Task5;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class DisplayCharts extends JFrame {
    private JPanel contentPane;
    private JRadioButton randomRadioButton;
    private JRadioButton findRadioButton;
    private JRadioButton seizeRadioButton;
    private JPanel topPanel;
    private final CardLayout cardLayout = new CardLayout();

    public DisplayCharts(ChartPanel rand, ChartPanel find, ChartPanel seize) {
        topPanel.setLayout(cardLayout);
        topPanel.add(rand, "rand");
        topPanel.add(find, "find");
        topPanel.add(seize, "seize");
        ButtonGroup group = new ButtonGroup();
        group.add(randomRadioButton);
        group.add(findRadioButton);
        group.add(seizeRadioButton);
        rand.setDomainZoomable(true);
        find.setDomainZoomable(true);
        seize.setDomainZoomable(true);
        setContentPane(contentPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        randomRadioButton.addActionListener(e -> cardLayout.show(topPanel, "rand"));
        findRadioButton.addActionListener(e -> cardLayout.show(topPanel, "find"));
        seizeRadioButton.addActionListener(e -> cardLayout.show(topPanel, "seize"));
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }
}
