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
    private int m_health;
    private int m_mana;
    private Race m_race;
    
    public Character(String name, Race race)
    {
        m_id= m_globalId;
        m_name= name;
        m_race= race;

        // increment the global id
        m_globalId++;
    }
    
    // Returns true if move was successful, false otherwise.
    public abstract boolean move();

    // Default changeHP, can be overridden by concrete classes -- eg. some races might have tougher skin, etc.
    public void changeHP( int value ) {
        m_health= m_health+value;
        
        if( m_health>=MAXIMUM_HP )
        {
            m_health= 100;
        }
        else if( isDead() )
        {
            m_health= 0;
        }
    }

    // Default changeHP, can be overridden by concrete classes -- eg. some races might be magicians, etc.
    public void changeMP( int value ) {
        m_mana= m_mana+value;

        if( m_mana>=MAXIMUM_MP )
        {
            m_mana= 100;
        }
        else if( isOOM() )
        {
            m_mana= 0;
        }
    }

    public boolean isDead() {
        return m_health <= MINIMUM_HP;
    }

    // is Out Of Mana (OOM)
    public boolean isOOM() {
        return m_mana <= MINIMUM_MP;
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