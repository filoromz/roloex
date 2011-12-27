/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class Map {
    //Represents the map where Character and Terrain objects are placed on.
    public final static int MIN_X=0;
    public final static int MAX_X= 1000;

    public final static int MIN_Y= 0;
    public final static int MAX_Y= 1000;

    private static MapObject[][] m_map;
    
//    public Map( int width, int height ) 
    public static void init( int width, int height ) 
    {
    	if (width > MAX_X)
    	{
    		width = MAX_X;
    	}
    	if (height > MAX_Y)
    	{
    		height = MAX_Y;
    	}
        m_map= new MapObject[width][height];
    }

    public static boolean placeTerrain( Terrain terrain, int x, int y )
    {
        if( !isValidCoordinate( x, y ) )
        {
        	return false;
        }
        
        // If terrain has already been set, return false
        if( m_map[x][y] != null && m_map[x][y].getTerrain() != null )
        {
        	return false;
        }
        
        MapObject mapObject= new MapObject();
        mapObject.setTerrain( terrain );
        m_map[x][y]= mapObject;
        return true;
    }

    public static boolean addCharacter( Character character, int x, int y )
    {
        MapObject currLocation= m_map[x][y];
        if( currLocation.isCharacterListEmpty() )
        {
            currLocation.addCharacter( character );
            return true;
        }
        else
        {
            //check if the character is already in the list
            if ( currLocation.hasCharacter( character ) )
            {
                return false;
            }
            else
            {
                currLocation.addCharacter( character );
                return true;
            }
        }
    }

    public static boolean isValidCoordinate( int x, int  y )
    {
    	if (m_map == null)
    	{
    		return false;
    	}
        return x >= MIN_X && x <= getWidth() &&
                y >= MIN_Y && y <= getHeight();
    }
    
    public static int getWidth()
    {
    	return m_map.length;
    }
    
    public static int getHeight()
    {
    	return m_map[0].length;
    }
    
    public static Terrain getTerrain(int x, int y)
    {
        return m_map[x][y].getTerrain();
    }
}
