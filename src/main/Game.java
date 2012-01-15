package main;

import main.character.Direction;
import main.character.Hero;
import main.character.Race;
import main.item.DummyItem;
import main.item.HeartOfOfferingItem;
import main.item.PotionItem;
import main.map.WorldMap;
import main.map.terrain.Terrain;
import main.map.terrain.TerrainFactory;
import main.map.terrain.TerrainType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class Game implements ActionListener
{
    Hero character;
    Hero character2;
    
	public void init()
	{
        Action moveUp= new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if( !character.isDead() )
                {
                    character.move( Direction.UP, 1 );
                    refreshMap( character );
                }

                try {
                    step( "keypress" );
                } catch (InterruptedException e1) {
                }
            }
        };

        Action moveDown= new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if( !character.isDead() )
                {
                    character.move( Direction.DOWN, 1 );
                    refreshMap( character );
                }

                try {
                    step( "keypress" );
                } catch (InterruptedException e1) {
                }
            }
        };

        Action moveLeft= new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if( !character.isDead() )
                {
                    character.move( Direction.LEFT, 1 );
                    refreshMap( character );
                }

                try {
                    step( "keypress" );
                } catch (InterruptedException e1) {
                }
            }
        };

        Action moveRight= new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if( !character.isDead() )
                {
                    character.move( Direction.RIGHT, 1 );
                    refreshMap( character );
                }

                try {
                    step( "keypress" );
                } catch (InterruptedException e1) {
                }
            }
        };

        GUI.jtfInput.addActionListener(this);

        GUI.jtfInput.getInputMap().put( KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "movedown" ); //NOTE: THIS IS A HACK! ARROW KEY UP MEANS DECREASE Y-ORDINATE!
        GUI.jtfInput.getInputMap().put( KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "moveup" ); //NOTE: THIS IS A HACK! ARROW KEY DOWN MEANS INCREASE Y-ORDINATE!
        GUI.jtfInput.getInputMap().put( KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "moveleft" );
        GUI.jtfInput.getInputMap().put( KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "moveright" );
        GUI.jtfInput.getActionMap().put( "moveup", moveUp );
        GUI.jtfInput.getActionMap().put( "movedown", moveDown );
        GUI.jtfInput.getActionMap().put( "moveleft", moveLeft );
        GUI.jtfInput.getActionMap().put( "moveright", moveRight );

	    generateMap(); //TODO: Make this less hacky
        generateCharacters(); //TODO: Make this less hacky

        refreshMap( character );
	}

    private void generateCharacters() {
        // Hack this in for now for demonstration purposes ;D...
        character= new Hero( "Rommel", Race.HUMAN );
        character.changeHP( 10 );
        character2= new Hero( "Barlow", Race.HUMAN );
        character2.changeHP( 10 );

        // randomly place them somewhere!
        character.setPosition( (int)( Math.random() * WorldMap.getWidth() ), (int)( Math.random() * WorldMap.getHeight() ) );
        character2.setPosition( (int)( Math.random() * WorldMap.getWidth()), (int)( Math.random() * WorldMap.getHeight() ) );
        
        // Add them to the map!
        WorldMap.addCharacter(character, character.getPositionX(), character.getPositionY());
        WorldMap.addCharacter(character2, character2.getPositionX(), character2.getPositionY());

        JLabel label= new JLabel( createImageIcon("resources/character.png", "Our Hero" ), JLabel.CENTER );

        //Adds from left to right, top to bottom
        GUI.jtMap.remove(character.getPositionX() + character.getPositionY()*WorldMap.getHeight() );
        GUI.jtMap.add(label, character.getPositionX() + character.getPositionY()*WorldMap.getHeight());

        // Let's add the boss!
        label= new JLabel( createImageIcon("resources/enemy.jpg", "BOSS" ), JLabel.CENTER );
        GUI.jtMap.remove(character2.getPositionX() + character2.getPositionY()*WorldMap.getHeight());
        GUI.jtMap.add(label, character2.getPositionX() + character2.getPositionY()*WorldMap.getHeight());

        GridBagConstraints gridCons1 = new GridBagConstraints();
        gridCons1.anchor= GridBagConstraints.NORTH;
        gridCons1.gridx=1;
        gridCons1.gridy=0;
        GUI.m_frame.getContentPane().add( GUI.jtMap, gridCons1 );

        GUI.m_frame.pack();
        GUI.m_frame.setVisible(true);
    }

    private void generateMap()
    {
        //Creating a 10x10 map for now
		WorldMap.init( 10, 10 );
		int terrainTypeCount = TerrainType.values().length;
		Random random = new Random();
		for(int i= 0; i< WorldMap.getHeight(); i++)
		{
			for(int j= 0; j< WorldMap.getWidth(); j++)
			{
				placeRandomTerrain( terrainTypeCount, random, i, j );
			}
		}
    }

    private void placeRandomTerrain( int terrainTypeCount, Random random, int x, int y )
    {
        int value = random.nextInt(terrainTypeCount);
        TerrainType terrainType = TerrainType.values()[value];
        Terrain terrain = TerrainFactory.getTerrain(terrainType);
        WorldMap.placeTerrain(terrain, x, y);
        
        JLabel label= new JLabel( createImageIcon("resources/" + terrain.getMapSymbol() + ".jpg", terrain.getMapSymbol() ), JLabel.CENTER );

        //Adds from left to right, top to bottom
        GUI.jtMap.add(label);

        GridBagConstraints gridCons1 = new GridBagConstraints();
        gridCons1.anchor= GridBagConstraints.NORTH;
        gridCons1.gridx=1;
        gridCons1.gridy=0;
        GUI.m_frame.getContentPane().add( GUI.jtMap, gridCons1 );

        GUI.m_frame.pack();
        GUI.m_frame.setVisible(true);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void refreshMap( Hero character )
    {
        JLabel label;

        for(int i= 0; i != WorldMap.getHeight(); i++)
        {
            for(int j= 0; j != WorldMap.getWidth(); j++)
            {
                
                if( character.getPositionX()==j && character.getPositionY()==i )
                {
                    if( character2.getPositionX()==j && character2.getPositionY()==i && !character2.isDead() )
                    {
                        //Hero should be on top
                        /**
                         * Read a background image
                         */
                        BufferedImage bgImage = getBufferedImageFromImage( createImageIcon( "resources/" + WorldMap.getTerrain( j, i ).getMapSymbol() + ".jpg", "Terrain" ).getImage() ); //ImageOverlay.readImage("D:/Rommel/Desktop/roloex/src/main/resources/" + WorldMap.getTerrain( j, i ).getMapSymbol() + ".jpg");

                        /**
                         * Read a foreground image
                         */
                        BufferedImage fgImage = getBufferedImageFromImage( createImageIcon( "resources/character.png", "Hero" ).getImage() ); //ImageOverlay.readImage("D:/Rommel/Desktop/roloex/src/main/resources/character.png");

                        /**
                         * Do the overlay of foreground image on background image
                         */
                        BufferedImage overlayedImage = ImageOverlay.overlayImages(bgImage, fgImage);
                        ImageIcon icon= new ImageIcon( overlayedImage );

                        label= new JLabel( icon, JLabel.CENTER );
                        GUI.jtMap.remove( j + i*WorldMap.getHeight() );
                        GUI.jtMap.add(label, j+i*WorldMap.getHeight() );
                    }
                    else
                    {
                        /**
                         * Read a background image
                         */
                        BufferedImage bgImage = getBufferedImageFromImage(createImageIcon("resources/" + WorldMap.getTerrain(j, i).getMapSymbol() + ".jpg", "Terrain").getImage()); //ImageOverlay.readImage("D:/Rommel/Desktop/roloex/src/main/resources/" + WorldMap.getTerrain( j, i ).getMapSymbol() + ".jpg");

                        /**
                         * Read a foreground image
                         */
                        BufferedImage fgImage = getBufferedImageFromImage(createImageIcon("resources/character.png", "Hero").getImage()); //ImageOverlay.readImage("D:/Rommel/Desktop/roloex/src/main/resources/character.png");

                        /**
                         * Do the overlay of foreground image on background image
                         */
                        BufferedImage overlayedImage = ImageOverlay.overlayImages(bgImage, fgImage);
                        ImageIcon icon= new ImageIcon( overlayedImage );

                        label= new JLabel( icon, JLabel.CENTER );
                        GUI.jtMap.remove( j + i*WorldMap.getHeight() );
                        GUI.jtMap.add(label, j+i*WorldMap.getHeight() );
                    }
                }
                else
                {
                    if( character2.getPositionX()==j && character2.getPositionY()==i && !character2.isDead() )
                    {
                        /**
                         * Read a background image
                         */
                        BufferedImage bgImage = getBufferedImageFromImage( createImageIcon( "resources/" + WorldMap.getTerrain( j, i ).getMapSymbol() + ".jpg", "Terrain" ).getImage() ); //ImageOverlay.readImage("D:/Rommel/Desktop/roloex/src/main/resources/" + WorldMap.getTerrain( j, i ).getMapSymbol() + ".jpg");

                        /**
                         * Read a foreground image
                         */
                        BufferedImage fgImage = getBufferedImageFromImage( createImageIcon( "resources/enemy.png", "BOSS" ).getImage() ); //ImageOverlay.readImage("D:/Rommel/Desktop/roloex/src/main/resources/character.png");

                        /**
                         * Do the overlay of foreground image on background image
                         */
                        BufferedImage overlayedImage = ImageOverlay.overlayImages(bgImage, fgImage);
                        ImageIcon icon= new ImageIcon( overlayedImage );

                        label= new JLabel( icon, JLabel.CENTER );
                        GUI.jtMap.remove( j + i*WorldMap.getHeight() );
                        GUI.jtMap.add(label, j+i*WorldMap.getHeight() );
                    }
                    else
                    {
                        //TODO: In future, we can just refresh just the one tile that the character was on previous turn.
                        label= new JLabel( createImageIcon("resources/" + WorldMap.getTerrain( j, i ).getMapSymbol() + ".jpg", "Terrain" ), JLabel.CENTER );
                        GUI.jtMap.remove(j + i * WorldMap.getHeight());
                        GUI.jtMap.add(label, j+i*WorldMap.getHeight() );
                    }
                }
            }
        }

        GridBagConstraints gridCons1 = new GridBagConstraints();
        gridCons1.anchor= GridBagConstraints.NORTH;
        gridCons1.gridx=1;
        gridCons1.gridy=0;
        GUI.m_frame.getContentPane().add( GUI.jtMap, gridCons1 );

        StringBuffer sb= new StringBuffer();
        sb.append( "<html><center><font size=5>HERO STATS</font><font size=3> <br>" );
        sb.append( "<table>" );
        sb.append( " <tr><td><u>Name:</u></td> <td>" + character.getName() + "</td><br>");
        sb.append( " <td><u>Class:</u></td> <td> " + character.getRace().toString() + "</td><br>" );
        sb.append( " <td><u>Location:</u></td> <td> (" + character.getPositionX() + "," + character.getPositionY() + ") </td></tr><br>");
        sb.append( " <tr><td><u>HP:</u></td> <td> " + character.getHP() + "</td><br>" );
        sb.append( " <td><u>MP:</u></td> <td> " + character.getMP() + " </td></tr><br>" );
        sb.append(" <tr><td><u>Defence:</u></td> <td> " + character.getDefence() + "</td><br>" );
        sb.append( " <td><u>Accuracy:</u></td> <td> " + character.getAccuracy() + "</td></tr><br></table></html>" );
        
        GUI.displayCharacterStatus( sb.toString() );
        GUI.displayInventory( this, character );

        GUI.m_frame.pack();
        GUI.m_frame.setVisible(true);

    }

    private BufferedImage getBufferedImageFromImage(Image img)
    {
        //This line is important, this makes sure that the image is
        //loaded fully
        img = new ImageIcon(img).getImage();

        //Create the BufferedImage object with the width and height of the Image
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        //Create the graphics object from the BufferedImage
        Graphics g = bufferedImage.createGraphics();

        //Draw the image on the graphics of the BufferedImage
        g.drawImage(img, 0, 0, null);

        //Dispose the Graphics
        g.dispose();

        //return the BufferedImage
        return bufferedImage;
    }

    public void run() throws InterruptedException {
        //Rommel is the HERO.. hehee
        Hero character= (Hero) WorldMap.getCharacters().get(0);
        //Barlow is the ENEMY :O
        Hero character2= (Hero) WorldMap.getCharacters().get(1);
        //Barlow is holding the HOO!
        character2.pickUp( HeartOfOfferingItem.getInstance(), 1 );

        GUI.displayText( "Picking up items for demonstration purposes... \n" );
        character.pickUp( new DummyItem(), 1 );
        character.pickUp( new PotionItem(), 2 );
        character.pickUp( new PotionItem(), 2 );

        GUI.displayInventory( this, character );
        GUI.displayText("Barlow's (THE BOSS) location: " + "(" + character2.getPositionX() + "," + character2.getPositionY() + ") \n");
        GUI.displayText( "Your position: (" + character.getPositionX() + "," + character.getPositionY() + ") \n" );
        GUI.displayText( "What do you want to do? [Available commands: 'move', 'use'] \n" );
    }

    private void simulateBattle( Hero character, Hero character2 ) throws InterruptedException {
        if( character.isAdjacent( character2 ) && !character2.isDead() )
        {
            // Battle Simulation between two Character objects
            int tempAttack;
            if( !( character.isDead() || character2.isDead() ) )
            {
                if( !character.isDead() )
                {
                    tempAttack= character.attack();
                    character2.changeHP( -tempAttack );
                    GUI.displayText(character.getName() + " attacks " + character2.getName() + "! " + character2.getName() + " loses " + tempAttack + " HP points. " +
                            " Status; " + character.getName() + "'s HP: " + character.getHP() + " " + character2.getName() + "'s HP: " + character2.getHP() + "\n");
                }

                if( !character2.isDead() )
                {
                    tempAttack= character2.attack();
                    character.changeHP( -tempAttack );
                    GUI.displayText(character2.getName() + " retaliates and hits " + character.getName() + "! " + character.getName() + " loses " + tempAttack + " HP points." +
                            " Status; " + character.getName() + "'s HP: " + character.getHP() + " " + character2.getName() + "'s HP: " + character2.getHP() + "\n");
                }
            }

            if( character.isDead() )
            {
                GUI.displayText(" Awww.. Game over, you died :( \n");
            }

            if( character2.isDead() )
            {
                GUI.displayText("OMG! Congratulations! YOU HAVE DEFEATED THE BOSS! HOOOO! \n");
                character.pickUp( character2.getItems() );
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            step( GUI.jtfInput.getText() );
            refreshMap( character );
        } 
        catch ( InterruptedException ex ) {
            System.out.println( "Could not step through the game! " + ex.getMessage() );
        }
    }

    private void step( String userCommand ) throws InterruptedException {
        String command;
        Scanner lineScanner;
        if( !character.isDead() )
        {
            lineScanner= new Scanner( userCommand );
            command= lineScanner.next();

            if( command.equals( "move" ) )
            {
                int userDirection;
                if( !lineScanner.hasNext() )
                {
                    // Prompt the user to enter the second argument
                    GUI.displayText( "Please enter a direction on where you want to move: (1=left, 2=up, 3=right, 4=down ) Eg. move 4 \n" );
                }
                else
                {
                    try
                    {
                        userDirection= Integer.parseInt( lineScanner.next() );
                    }
                    catch( NumberFormatException e )
                    {
                        GUI.displayText( "An error occured when trying to parse your move command! " + e.getMessage() + "\n" );
                        userDirection= -1;
                    }

                    switch( userDirection )
                    {
                        case 1:
                            character.move( Direction.LEFT, 1 );
                            break;
                        case 2:
                            character.move( Direction.UP, 1 );
                            break;
                        case 3:
                            character.move( Direction.RIGHT, 1 );
                            break;
                        case 4:
                            character.move( Direction.DOWN, 1 );
                            break;
                        default:
                            //don't move the character
                            GUI.displayText( "Invalid move! \n" );
                            break;
                    }
                }
            }
            else if( command.equals( "use" ) )
            {
                String userItem;
                if( !lineScanner.hasNext() )
                {
                    // Prompt the user to enter the second argument and list the available items.
                    GUI.displayText( "Please select which item do you want to use? use <item-name> \n" );
                    character.printItems();
                }
                else
                {
                    userItem= lineScanner.next();
                    character.useItem( userItem );
                }
            }
            //Character should automatically move
            else if( command.equals( "keypress" ) ) { }
            else
            {
                GUI.displayText( "Invalid command. Please try again \n" );
            }

            simulateBattle( character, character2 );

            //Don't spam the console if user is using arrow presses
            if( !command.equals( "keypress" ) )
            {
                GUI.displayText( "Your position: (" + character.getPositionX() + "," + character.getPositionY() + ") \n" );
                GUI.displayText( "What do you want to do? [Available commands: 'move' (or with arrow keys), 'use'] \n" );
            }
        }
    }
}
