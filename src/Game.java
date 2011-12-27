import java.util.Random;
import java.util.Scanner;

public class Game
{
	public void init()
	{
	    generateMap();
        generateCharacters();
	}

    private void generateCharacters() {
        // Hack this in for now for demonstration purposes ;D...
        Character character= new Hero( "Rommel", Race.HUMAN );
        character.changeHP( 10 );
        Character character2= new Hero( "Barlow", Race.HUMAN );
        character2.changeHP( 10 );

        // randomly place them somwhere!
        character.setPosition( (int)( Math.random() * Map.getWidth() ), (int)( Math.random() * Map.getHeight() ) );
        character2.setPosition( (int)( Math.random() * Map.getWidth()), (int)( Math.random() * Map.getHeight() ) );
        
        // Add them to the map!
        Map.addCharacter( character, character.getPositionX(), character.getPositionY() );
        Map.addCharacter( character2, character2.getPositionX(), character2.getPositionY() );
    }

    private void generateMap()
    {
        //Creating a 10x10 map for now
		Map.init(10, 10);
		int terrainTypeCount = TerrainType.values().length;
		Random random = new Random();
		for(int i = 0; i < Map.getHeight(); i++)
		{
			for(int j = 0; j < Map.getWidth(); j++)
			{
				placeRandomTerrain(terrainTypeCount, random, i, j);
			}
			System.out.println();
		}
    }

    private void placeRandomTerrain(int terrainTypeCount, Random random, int x, int y)
    {
        int value = random.nextInt(terrainTypeCount);
        TerrainType terrainType = TerrainType.values()[value];
        Terrain terrain = TerrainFactory.getTerrain(terrainType);
        Map.placeTerrain(terrain, y, x);
        
        System.out.print(terrain.getTerrainType() + ",");
    }

    public void run() throws InterruptedException {
        //Rommel is the HERO.. hehee
        Character character= Map.getCharacters().get(0);
        //Barlow is the ENEMY :O
        Character character2= Map.getCharacters().get(1);

        Scanner scanner= new Scanner( System.in );
        int userDirection;
        
        System.out.println( "Barlow's (THE BOSS) location: " + "(" + character2.getPositionX() + "," + character2.getPositionY() + ")" );
        while( !character.isDead() && !character.isAdjacent( character2 ) )
        {
            System.out.println( "Your position: (" + character.getPositionX() + "," + character.getPositionY() + ")" );
            System.out.println( "Where do you want to move? (1=left, 2=up, 3=right, 4=down )" );
            userDirection= Integer.parseInt( scanner.next() );
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
                    System.out.println( "Invalid move!" );
                    break;
            }
        }            
            
        System.out.println( "OMG! It's a ROLO battle!" );
        int tempAttack;
        
        while( !( character.isDead() || character2.isDead() ) )
        {
            if( !character.isDead() )
            {
                tempAttack= character.attack();
                character2.changeHP( -tempAttack );
                System.out.println( character.getName() + " attacks " + character2.getName() + "! " + character2.getName() + " loses " + tempAttack + " HP points. " +
                    " Status; " + character.getName() + "'s HP: " + character.getHP() + " " + character2.getName() + "'s HP: " + character2.getHP() );
            }

            if( !character2.isDead() )
            {
                tempAttack= character2.attack();
                character.changeHP( -tempAttack );
                System.out.println( character2.getName() + " retaliates and hits " + character.getName() + "! " + character.getName() + " loses " + tempAttack + " HP points." +
                        " Status; " + character.getName() + "'s HP: " + character.getHP() + " " + character2.getName() + "'s HP: " + character2.getHP() );
            }
            Thread.sleep( 1000 );
        }

        if( character.isDead() )
        {
            System.out.println( " Awww.. Game over, you died :(" );
        }
        else
        {
            System.out.println( "OMG! Congratulations! YOU HAVE DEFEATED THE BOSS! HOOOO!" );
        }
    }
}
