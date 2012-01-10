package main;

import javax.swing.*;
import javax.swing.text.Document;
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
    public static JTextField jtfInput;
    public static JTextArea jtAreaOutput;
    public static JEditorPane jtMap;
    public static JTextArea jtInventory;
    public static String newline = "\n";
    public GUI() {
        createGui();
    }
    public void createGui() {
        //Make the window:
        JFrame frame= new JFrame("ROLOEX RPG v1.0");
        frame.setPreferredSize( new Dimension(1200,500) );
        frame.setResizable( false );
        frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        jtfInput = new JTextField(75);
        jtfInput.addActionListener(this);

        jtAreaOutput = new JTextArea(25, 75);
        jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument()
                .getLength());
        jtAreaOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(jtAreaOutput,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        jtMap = new JEditorPane("text/html","");
        jtMap.setCaretPosition(jtMap.getDocument()
                .getLength());
        jtMap.setEditable(false);


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
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(gridBag);
        GridBagConstraints gridCons1 = new GridBagConstraints();

        gridCons1.gridx=0;
        gridCons1.gridy=1;
        contentPane.add(jtfInput, gridCons1);

        gridCons1.gridx=0;
        gridCons1.gridy=0;
        contentPane.add(scrollPane, gridCons1);
        displayText( "~ MAIN CONSOLE ~ \n\n" );

        gridCons1.anchor= GridBagConstraints.NORTH;
        gridCons1.gridx=1;
        gridCons1.gridy=0;
        gridCons1.fill= GridBagConstraints.HORIZONTAL;
        contentPane.add(jtMap, gridCons1);

        gridCons1.anchor= GridBagConstraints.SOUTH;
        gridCons1.gridx=1;
        gridCons1.gridy=0;
        contentPane.add(scrollPane2, gridCons1);
        displayInventory( "~ ITEM EVENTS ~ \n\n" );

        frame.pack();
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent evt) {
        String text = jtfInput.getText();
        jtAreaOutput.append(text + newline);
        jtfInput.selectAll();
    }

    public static void displayText(String s) {
        jtAreaOutput.append(s);
    }

    public static void displayMap(String s) {
        jtMap.setText(s);
    }

    public static void displayInventory(String s) {
        jtInventory.append(s);
    }

    public static void clearMap() {
        Document doc = jtMap.getDocument();
        try {
            doc.remove(0,doc.getLength());
        }
        catch(Throwable e) { }
        //TODO: We should probably catch this!!
    }
}