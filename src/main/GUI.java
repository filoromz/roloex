package main;

import main.character.Hero;
import main.item.Item;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 3/01/12
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class GUI implements ActionListener
{
    public static JTextField jtfInput = new JTextField( 75 );
    public static JTextArea jtAreaOutput;
    public static JPanel jtMap;
    public static JPanel jtInventory;
    public static JFrame m_frame;
    public static String newline = "\n";
    private static JLabel m_charStatus;

    public GUI()
    {
        createGui();
    }

    public void createGui()
    {
        //Make the window:
        m_frame = new JFrame( "ROLOEX RPG v1.0" );
        m_frame.setPreferredSize( new Dimension( 1200, 600 ) );
        m_frame.setResizable( false );
        m_frame.addWindowListener( new WindowAdapter()
        {
            @Override
            public void windowClosing( WindowEvent e )
            {
                System.exit( 0 );
            }
        } );

        jtfInput.addActionListener( this );

        jtAreaOutput = new JTextArea( 30, 75 );
        jtAreaOutput.setEditable( false );
        // TO autoscroll down
        DefaultCaret caret = (DefaultCaret) jtAreaOutput.getCaret();
        caret.setUpdatePolicy( DefaultCaret.ALWAYS_UPDATE );

        JScrollPane scrollPane = new JScrollPane( jtAreaOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );

        GridBagLayout gridBag = new GridBagLayout();
        Container contentPane = m_frame.getContentPane();
        contentPane.setLayout( gridBag );
        GridBagConstraints gridCons1 = new GridBagConstraints();

        gridCons1.gridx = 0;
        gridCons1.gridy = 0;
        contentPane.add( scrollPane, gridCons1 );
        displayText( "~ MAIN CONSOLE ~ \n\n" );

        gridCons1.gridx = 0;
        gridCons1.gridy = 1;
        contentPane.add( jtfInput, gridCons1 );

        // Map JPanel
        GridLayout gridLayout = new GridLayout( 10, 10 );
        jtMap = new JPanel( gridLayout );

        // Char Stats JLabel
        m_charStatus = new JLabel();

        // Inventory JPanel
        gridLayout = new GridLayout( 2, 5 );
        jtInventory = new JPanel( gridLayout );

        GridLayout gLayout = new GridLayout( 2, 1 );
        gLayout.setVgap( 10 );
        gLayout.setHgap( 10 );

        JPanel mapPanel = new JPanel( gLayout );
        mapPanel.add( jtMap );
        mapPanel.add( m_charStatus );
        mapPanel.add( jtInventory );

        // Add the mapPanel to the contentPane of the ROLOEX Frame.
        gridCons1.anchor = GridBagConstraints.SOUTH;
        gridCons1.gridx = 1;
        gridCons1.gridy = 0;
        contentPane.add( mapPanel, gridCons1 );
    }

    public void actionPerformed( ActionEvent evt )
    {
        String text = jtfInput.getText();
        jtAreaOutput.append( text + newline );
        jtfInput.selectAll();
    }

    public static void displayText( String s )
    {
        jtAreaOutput.append( s );
    }

    public static void displayInventory( Game game, Hero hero )
    {
        // Clear the item list and repopulate
        GUI.jtInventory.removeAll();

        Map<Item, Integer> map = hero.getItems();
        JLabel label;
        int counter = 0;
        for( Item item : map.keySet() )
        {
            label = new JLabel( "x" + map.get( item ).toString(), createImageIcon( game, "resources/items/" + item.getSymbol() + ".png", item.getName() ), JLabel.CENTER );
            label.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED ) );
            GUI.jtInventory.add( label );
            counter++;
        }
        GridLayout temp = (GridLayout) GUI.jtInventory.getLayout();

        while( counter!=temp.getRows()*temp.getColumns() )
        {
            //Lets fill the rest with dummy labels:
            label = new JLabel();
            label.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED ) );
            GUI.jtInventory.add( label );
            counter++;
        }

        GUI.m_frame.pack();
        GUI.m_frame.setVisible( true );
    }

    public static void displayCharacterStatus( String s )
    {
        m_charStatus.setText( s );
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon( Game game, String path, String description )
    {
        URL imgURL = game.getClass().getResource( path );
        if( imgURL!=null )
        {
            return new ImageIcon( imgURL, description );
        }
        else
        {
            System.err.println( "Couldn't find file: " + path );
            return null;
        }
    }

}
