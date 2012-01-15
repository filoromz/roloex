package main.map;

import main.character.Character;
import main.map.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class MapObject
{

    //List of characters on this location
    private List<Character> m_characters = new ArrayList<Character>();

    // can only have one terrain on one location.
    private Terrain m_terrain;

    public void addCharacter( Character character )
    {
        m_characters.add( character );
    }

    public void removeCharacter( Character character )
    {
        m_characters.remove( character );
    }

    public boolean isCharacterListEmpty()
    {
        return m_characters.isEmpty();
    }

    public Terrain getTerrain()
    {
        return m_terrain;
    }

    public void setTerrain( Terrain terrain )
    {
        m_terrain = terrain;
    }

    public boolean hasCharacter( Character character )
    {
        for( Character temp : m_characters )
        {
            if( temp.getId()==character.getId() )
            {
                return true;
            }
        }
        return false;
    }
}
