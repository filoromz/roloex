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
    
    protected int m_positionX;
    protected int m_positionY;
    
    public Character(String name, Race race)
    {
        m_id= m_globalId;
        m_name= name;
        m_race= race;

        // increment the global id
        m_globalId++;

        // randomly place him somwhere!
        m_positionX= (int) ( Math.random() * Map.MAX_X );
        m_positionY= (int) ( Math.random() * Map.MAX_Y );
    }
    
    public int getPositionX()
    {
        return m_positionX;
    }
    
    public int getPositionY()
    {
        return m_positionY;
    }
    
    // Returns true if move was successful, false otherwise.
    public abstract boolean move( Direction direction, int distance );

    // Returns true if attack was successful, false otherwise.
    public abstract boolean attack();

    // Default changeHP, can be overridden by concrete classes -- eg. some races might have tougher skin, etc.
    public void changeHP( int value ) {
        m_hp = m_hp + value;
        
        if( m_hp>=MAXIMUM_HP )
        {
            m_hp = 100;
        }
        else if( isDead() )
        {
            m_hp = 0;
        }
    }

    // Default changeHP, can be overridden by concrete classes -- eg. some races might be magicians, etc.
    public void changeMP( int value ) {
        m_mp = m_mp + value;

        if( m_mp >=MAXIMUM_MP )
        {
            m_mp = 100;
        }
        else if( isOOM() )
        {
            m_mp = 0;
        }
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