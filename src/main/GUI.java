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
    public static JTextField jtfInput;
    public static JTextArea jtAreaOutput;
    public static JTextArea jtMap;
    public static JTextArea jtInventory;
    public static String newline = "\n";
    public GUI() {
        createGui();
    }
    public void createGui() {
        //Make the window:
        JFrame frame= new JFrame("ROLOEX RPG v1.0");
        frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        jtfInput = new JTextField(75);
        jtfInput.addActionListener(this);
        jtAreaOutput = new JTextArea(50, 75);
        jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument()
                .getLength());
        jtAreaOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(jtAreaOutput,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        jtMap = new JTextArea(25, 50);
        jtMap.setCaretPosition(jtMap.getDocument()
                .getLength());
        jtMap.setEditable(false);


        jtInventory = new JTextArea(25, 50);
        jtInventory.setCaretPosition(jtInventory.getDocument()
                .getLength());
        jtInventory.setEditable(false);

        // TO autoscroll down.
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
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
        contentPane.add(jtMap, gridCons1);
        displayMap( "~ WORLD MAP ~ \n\n" );

        gridCons1.anchor= GridBagConstraints.SOUTH;
        gridCons1.gridx=1;
        gridCons1.gridy=0;
        contentPane.add(jtInventory, gridCons1);
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
        jtMap.append(s);
    }

    public static void displayInventory(String s) {
        jtInventory.append(s);
    }
}
