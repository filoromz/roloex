/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class Map {
    //Represents the map where Character and Terrain objects are placed on.
    private final static int MAX_X= 1000;
    private final static int MAX_Y= 1000;
    private static MapObject[][] m_map;
    
    public Map( int x, int y ) {
        m_map= new MapObject[x][y];
    }

    public boolean placeTerrain( Terrain terrain, int x, int y )
    {
        if( m_map[x][y].getTerrain()==null )
        {
            MapObject mapObject= new MapObject();
            mapObject.setTerrain( terrain );
            m_map[x][y]= mapObject;
            return true;
        }
        return false;
    }

    public boolean addCharacter( Character character, int x, int y )
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
}
