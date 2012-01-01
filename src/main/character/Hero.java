package main.character;

import main.item.Item;
import main.map.WorldMap;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 26/12/11
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Hero extends Character {

    public Hero( String name, Race race ) {
        super( name, race );
    }

    @Override
    public boolean move( Direction direction, int distance ) {
        int tempX= m_positionX;
        int tempY= m_positionY;
        
        switch( direction )
        {
            case LEFT:
                m_positionX= m_positionX - distance;
                break;
            case RIGHT:
                m_positionX= m_positionX + distance;
                break;
            case DOWN:
                m_positionY= m_positionY - distance;
                break;
            case UP:
                m_positionY= m_positionY + distance;
                break;
            default:
                return false;
        }
        
        if( WorldMap.isValidCoordinate(m_positionX, m_positionY) )
        {
            return true;
        }
        else
        {
            // put it back as it was before
            m_positionX= tempX;
            m_positionY= tempY;
            return false;
        }
    }

    @Override
    public int attack() {
        // Hit between 0-2 for now with 100% accuracy!
        return (int) ( Math.random() * 3 * m_accuracy );
    }
    
    // pickUp method for a single item with a particular quantity.
    public void pickUp( Item item, int quantity )
    {
        String itemName= item.getName();
        if (m_items.containsKey(itemName))
        {
            // Just increment the quantity of the item.
            int qty = m_items.get(itemName) + quantity;
            m_items.put(itemName, qty);

            System.out.println("ITEM=" + item.getName() + " QTY=" + m_items.get(itemName));
        }
        else
        {
            System.out.println("Adding " + item.getName() + " to Character's stash.");
            m_items.put(itemName, quantity);

            System.out.println("ITEM=" + item.getName() + " QTY=" + m_items.get(itemName));
        }
    }

    // pickUp method for a 'stash' of items after killing another Character object.
    public void pickUp( Map<Item,Integer> items )
    {
        // Need to iterate through the Character's stash in the argument.
        for ( Item item : items.keySet() ) {
            pickUp( item, items.get( item ) );
        }
    }

    public boolean hasItem( Item item ) {
        return m_items.containsKey( item.getName() );
    }
}
