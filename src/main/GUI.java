package main;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private static JLabel m_charStatus;

    public GUI() {
        createGui();
    }

    public void createGui() {
        //Make the window:
        m_frame= new JFrame("ROLOEX RPG v1.0");
        m_frame.setPreferredSize( new Dimension(1300,800) );
        m_frame.setResizable( false );
        m_frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        jtfInput.addActionListener(this);

        jtAreaOutput = new JTextArea(40, 75);
        jtAreaOutput.setEditable(false);
        // TO autoscroll down
        DefaultCaret caret = (DefaultCaret)jtAreaOutput.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scrollPane = new JScrollPane(jtAreaOutput,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        jtInventory = new JTextArea(10, 30);
        jtInventory.setEditable(false);
        // TO autoscroll down
        DefaultCaret caret2 = (DefaultCaret)jtInventory.getCaret();
        caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scrollPane2 = new JScrollPane(jtInventory,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        GridBagLayout gridBag = new GridBagLayout();
        Container contentPane = m_frame.getContentPane();
        contentPane.setLayout(gridBag);
        GridBagConstraints gridCons1 = new GridBagConstraints();

        gridCons1.gridx=0;
        gridCons1.gridy=0;
        contentPane.add(scrollPane, gridCons1);
        displayText( "~ MAIN CONSOLE ~ \n\n" );

        gridCons1.gridx=0;
        gridCons1.gridy=1;
        contentPane.add(jtfInput, gridCons1);

        m_gridLayout= new GridLayout(10,10);
        m_gridLayout.setHgap(0);
        m_gridLayout.setVgap(0);

        jtMap= new JPanel( m_gridLayout );

        m_charStatus= new JLabel();
        GridLayout gLayout= new GridLayout(2, 1);

        JPanel mapPanel= new JPanel( gLayout );
        mapPanel.add(jtMap);
        mapPanel.add( m_charStatus );
        mapPanel.add( scrollPane2 );

        gridCons1.anchor= GridBagConstraints.SOUTH;
        gridCons1.gridx=1;
        gridCons1.gridy=0;
        contentPane.add(mapPanel, gridCons1);
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
    
    public static void displayCharacterStatus( String s ) {
        m_charStatus.setText( s );
    }

}
