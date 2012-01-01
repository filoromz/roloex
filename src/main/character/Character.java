package main.character;

import main.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Character {
    private final static int MAXIMUM_HP= 100;
    private final static int MINIMUM_HP= 0;
    
    private final static int MAXIMUM_MP= 100;
    private final static int MINIMUM_MP= 0;
    
    private static int m_globalId= 0;
    
    private int m_id;
    private String m_name;
    private int m_hp;
    private int m_mp;
    private Race m_race;
    private int m_defence;
    protected Map<Item, Integer> m_items;
    protected Map<String, Item> m_stringToItems;

    protected double m_accuracy;
    protected int m_positionX;
    protected int m_positionY;
    
    public Character(String name, Race race)
    {
        m_id= m_globalId;
        m_name= name;
        m_race= race;

        // Default for now:
        m_defence= 0;
        m_accuracy= 1.0;

        // increment the global id
        m_globalId++;
        m_items= new HashMap<Item, Integer>();
        m_stringToItems = new HashMap<String, Item>();
    }
    
    public Map<Item, Integer> getItems()
    {
        return m_items;
    }
    
    public int getPositionX()
    {
        return m_positionX;
    }
    
    public int getPositionY()
    {
        return m_positionY;
    }

    public void setPosition( int x, int y )
    {
        m_positionX= x;
        m_positionY= y;
    }
    
    // Returns true if move was successful, false otherwise.
    public abstract boolean move( Direction direction, int distance );

    // Returns the attack damage as an int
    public abstract int attack();
    
    public boolean isAdjacent( Character character )
    {
        if( character==null )
        {
            return false;
        }
        else
        {
            // Need to check if the character parameter is in 9 spots:            
            // First column:
            if( m_positionX==character.getPositionX()-1 && 
                    ( m_positionY==character.getPositionY()+1 ||
                      m_positionY==character.getPositionY() ||
                      m_positionY==character.getPositionY()-1 ) ) { return true; }
            
            // Second column:
            if( m_positionX==character.getPositionX() &&
                    ( m_positionY==character.getPositionY()+1 ||
                      m_positionY==character.getPositionY() ||
                      m_positionY==character.getPositionY()-1 ) ) { return true; }

            // Third column:
            if( m_positionX==character.getPositionX()+1 &&
                    ( m_positionY==character.getPositionY()+1 ||
                      m_positionY==character.getPositionY() ||
                      m_positionY==character.getPositionY()-1 ) ) { return true; }

            // Failed the previous 3 if statements, therefore is not adjacent!
            return false;
        }
    }

    // Default changeHP, can be overridden by concrete classes -- eg. some races might have tougher skin, etc.
    public void changeHP( int value ) {
        m_hp= m_hp + value;
        
        if( m_hp>=MAXIMUM_HP )
        {
            m_hp= MAXIMUM_HP;
        }
        else if( isDead() )
        {
            m_hp= MINIMUM_HP;
        }
    }

    // Default changeHP, can be overridden by concrete classes -- eg. some races might be magicians, etc.
    public void changeMP( int value ) {
        m_mp= m_mp + value;

        if( m_mp>=MAXIMUM_MP )
        {
            m_mp= MAXIMUM_MP;
        }
        else if( isOOM() )
        {
            m_mp= MINIMUM_MP;
        }
    }
    
    public int getHP()
    {
        return m_hp;
    }
    
    public int getMP()
    {
        return m_mp;
    }

    public boolean isDead() {
        return m_hp <= MINIMUM_HP;
    }

    // is Out Of Mana (OOM)
    public boolean isOOM() {
        return m_mp <= MINIMUM_MP;
    }
    
    public String getName()
    {
        return m_name;
    }
    
    public Race getRace()
    {
        return m_race;
    }

    public int getId()
    {
        return m_id;
    }
}