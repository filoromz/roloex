package main.item;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 31/12/11
 * Time: 9:23 PM
 * Represents an item that can be held by a Character object
 */
public abstract class Item {
    private String m_name;
    protected ItemType m_type;
    
    public Item( String name, ItemType type )
    {
        m_name= name;
        m_type= type;
    }

    // Use the Item
    public abstract void use();
    
    public String getName()
    {
        return m_name;
    }
    
}
