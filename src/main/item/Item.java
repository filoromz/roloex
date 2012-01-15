package main.item;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 31/12/11
 * Time: 9:23 PM
 * Represents an item that can be held by a Character object
 */
public abstract class Item
{
    private String m_name;
    protected ItemType m_type;
    protected String m_symbol;

    public Item( String name, ItemType type, String symbol )
    {
        m_name = name;
        m_type = type;
        m_symbol = symbol;
    }

    // Use the Item
    public abstract HashMap<ItemEffects, Integer> use();

    public String getName()
    {
        return m_name;
    }

    public String getSymbol()
    {
        return m_symbol;
    }

    public ItemType getType()
    {
        return m_type;
    }
}
