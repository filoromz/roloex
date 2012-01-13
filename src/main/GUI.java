package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 3/01/12
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class GUI implements ActionListener {
    public static JTextField jtfInput= new JTextField(75);
    public static JTextArea jtAreaOutput;
    public static JPanel jtMap;
    public static JTextArea jtInventory;
    public static JFrame m_frame;
    public static String newline = "\n";
    public static GridLayout m_gridLayout;

    public GUI() {
        createGui();
    }

    public void createGui() {
        //Make the window:
        m_frame= new JFrame("ROLOEX RPG v1.0");
        m_frame.setPreferredSize( new Dimension(1300,700) );
        m_frame.setResizable( false );
        m_frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        jtfInput.addActionListener(this);

        jtAreaOutput = new JTextArea(35, 75);
        jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument()
                .getLength());
        jtAreaOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(jtAreaOutput,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        jtInventory = new JTextArea(10, 30);
        jtInventory.setCaretPosition(jtInventory.getDocument()
                .getLength());
        jtInventory.setEditable(false);

        JScrollPane scrollPane2 = new JScrollPane(jtInventory,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // TO autoscroll down.
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }});

        scrollPane2.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }});

        GridBagLayout gridBag = new GridBagLayout();
        Container contentPane = m_frame.getContentPane();
        contentPane.setLayout(gridBag);
        GridBagConstraints gridCons1 = new GridBagConstraints();

        gridCons1.gridx=0;
        gridCons1.gridy=1;
        contentPane.add(jtfInput, gridCons1);

        gridCons1.gridx=0;
        gridCons1.gridy=0;
        contentPane.add(scrollPane, gridCons1);
        displayText( "~ MAIN CONSOLE ~ \n\n" );

        m_gridLayout= new GridLayout(10,10);
        m_gridLayout.setHgap(0);
        m_gridLayout.setVgap(0);

        jtMap= new JPanel( m_gridLayout );

        gridCons1.anchor= GridBagConstraints.SOUTH;
        gridCons1.gridx=1;
        gridCons1.gridy=0;
        contentPane.add(scrollPane2, gridCons1);
        displayInventory( "~ ITEM EVENTS ~ \n\n" );
    }

    public void actionPerformed(ActionEvent evt) {
        String text = jtfInput.getText();
        jtAreaOutput.append(text + newline);
        jtfInput.selectAll();
    }

    public static void displayText(String s) {
        jtAreaOutput.append(s);
    }

    public static void displayInventory(String s) {
        jtInventory.append(s);
    }

}
