package main;
import main.character.Character;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Game implements ActionListener
{
    public static String userCommand= null;
    public static String userArg= null;
    //public static String userItem= null;

	public void init()
	{
        GUI.jtfInput.addActionListener(this);
	    generateMap(); //TODO: Make this less hacky
        generateCharacters(); //TODO: Make this less hacky
	}

    private void generateCharacters() {
        // Hack this in for now for demonstration purposes ;D...
        Character character= new Hero( "Rommel", Race.HUMAN );
        character.changeHP( 10 );
        Character character2= new Hero( "Barlow", Race.HUMAN );
        character2.changeHP( 10 );

        // randomly place them somewhere!
        character.setPosition( (int)( Math.random() * WorldMap.getWidth() ), (int)( Math.random() * WorldMap.getHeight() ) );
        character2.setPosition( (int)( Math.random() * WorldMap.getWidth()), (int)( Math.random() * WorldMap.getHeight() ) );
        
        // Add them to the map!
        WorldMap.addCharacter(character, character.getPositionX(), character.getPositionY());
        WorldMap.addCharacter(character2, character2.getPositionX(), character2.getPositionY());
    }

    private void generateMap()
    {
        //Creating a 10x10 map for now
		WorldMap.init(10, 10);
		int terrainTypeCount = TerrainType.values().length;
		Random random = new Random();
		for(int i = 0; i < WorldMap.getHeight(); i++)
		{
			for(int j = 0; j < WorldMap.getWidth(); j++)
			{
				placeRandomTerrain(terrainTypeCount, random, i, j);
			}
            GUI.displayMap("\n");
		}
    }

    private void placeRandomTerrain( int terrainTypeCount, Random random, int x, int y )
    {
        int value = random.nextInt(terrainTypeCount);
        TerrainType terrainType = TerrainType.values()[value];
        Terrain terrain = TerrainFactory.getTerrain(terrainType);
        WorldMap.placeTerrain(terrain, x, y);

        GUI.displayMap( terrain.getTerrainType() + "," );
    }

    public void run() throws InterruptedException {
        //Rommel is the HERO.. hehee
        Hero character= (Hero) WorldMap.getCharacters().get(0);
        //Barlow is the ENEMY :O
        Hero character2= (Hero) WorldMap.getCharacters().get(1);
        //Barlow is holding the HOO!
        character2.pickUp( HeartOfOfferingItem.getInstance(), 1 );

        GUI.displayText( "Picking up items for demonstration purposes... \n" );
        character.pickUp(new DummyItem(), 3);
        character.pickUp( new DummyItem(), 1 );
        character.pickUp( new PotionItem(), 2 );

        Scanner lineScanner;

        String command;

        GUI.displayText("Barlow's (THE BOSS) location: " + "(" + character2.getPositionX() + "," + character2.getPositionY() + ") \n");
        
        while( !character.isDead() )
        {
            GUI.displayText( "Your position: (" + character.getPositionX() + "," + character.getPositionY() + ") \n" );
            GUI.displayText( "What do you want to do? [Available commands: 'move', 'use']" );

            while( userCommand==null || userCommand.equals("") ) { Thread.sleep(1000); } //TODO: There HAS to be a better way than this :(

            //System.out.println("We are here!");
            lineScanner= new Scanner( userCommand );
            //System.out.println("After the lineScanner is set!");
            command= lineScanner.next();
            //System.out.println("command=" + command );

            if( command.equals( "move" ) )
            {
                int userDirection;
                if( !lineScanner.hasNext() )
                {
                    // Prompt the user to enter the second argument
                    GUI.displayText( "Where do you want to move? (1=left, 2=up, 3=right, 4=down ) \n" );
                    while( userArg==null || userArg.equals("") ) { Thread.sleep(1000); } //TODO: There HAS to be a better way than this :(
                    userDirection= Integer.parseInt( userArg );
                    userCommand=null;
                    userArg=null;
                }
                else
                {
                    try
                    {
                        userDirection= Integer.parseInt( lineScanner.next() );
                        userCommand=null;
                    }
                    catch( NumberFormatException e )
                    {
                        GUI.displayText( "An error occured when trying to parse your move command! " + e.getMessage() + "\n" );
                        userDirection= -1;
                    }
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
            else if( command.equals( "use" ) )
            {
                String userItem;
                if( !lineScanner.hasNext() )
                {
                    // Prompt the user to enter the second argument and list the available items.
                    GUI.displayText( "Which item do you want to use? \n" );
                    character.printItems();
                    while( userArg==null || userArg.equals("") )
                    { Thread.sleep(1000); }
                    userItem= userArg;
                    userCommand=null;
                    userArg=null;
                }
                else
                {
                    userItem= lineScanner.next();
                    userCommand=null;
                    userArg=null;
                }
                character.useItem( userItem );
            }
            else
            {
                GUI.displayText( "Invalid command. Please try again \n" );
                userCommand=null;
                userArg=null;
            }
            
            if( character.isAdjacent( character2 ) && !character2.isDead() )
            {
                simulateBattle( character, character2 );
            }
        }
    }

    private void simulateBattle( Hero character, Hero character2 ) throws InterruptedException {
        // Battle Simulation between two Character objects            
        GUI.displayText("OMG! It's a ROLO battle! \n");
        int tempAttack;
        while( !( character.isDead() || character2.isDead() ) )
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
            Thread.sleep( 1000 );
        }

        if( character.isDead() )
        {
            GUI.displayText(" Awww.. Game over, you died :( \n");
        }
        else
        {
            GUI.displayText("OMG! Congratulations! YOU HAVE DEFEATED THE BOSS! HOOOO! \n");
            character.pickUp( character2.getItems() );
        }
    }

    public void actionPerformed(ActionEvent e) {
        if( userCommand==null )
        {
            userCommand= GUI.jtfInput.getText();
        }
        else
        {
            userArg= GUI.jtfInput.getText();
        }
    }
}
