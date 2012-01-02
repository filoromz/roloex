package main.character;

import main.item.Item;
import main.item.ItemEffects;
import main.item.ItemType;
import main.map.WorldMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        // Need to store two types of HashMaps. One which will map the String name of the item with the Item instance and one which will map the Item instance with its quantity.
        // We need the HashMap<String,Item> because we can't use Item as a key as Java recognises each new Item instance as a different 'key' and we will get duplicates in the HashMap.
        String itemName= item.getName();
        if ( m_stringToItems.containsKey(itemName) )
        {
            // Need to get the reference to the first Item object stored in the HashMap or else we'll have duplicates!
            Item tempItem= m_stringToItems.get( itemName );
            
            // Just increment the quantity of the item.
            int qty= m_items.get(tempItem) + quantity;
            m_items.put(tempItem, qty);

            System.out.println( "ITEM=" + tempItem.getName() + " QTY=" + m_items.get( tempItem ) );
        }
        else
        {
            System.out.println( "Adding " + item.getName() + " to Character " + this.getName() + " stash." );
            m_items.put( item, quantity );
            m_stringToItems.put( itemName, item );

            System.out.println("ITEM=" + item.getName() + " QTY=" + m_items.get(item));
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

    public Item getItemFromSymbol( String itemSymbol ) {
        Set set= m_items.entrySet();
        Iterator setIterator= set.iterator();

        while( setIterator.hasNext() )
        {
            Map.Entry me= (Map.Entry) setIterator.next();
            Item tempItem= (Item) me.getKey();
            if( tempItem.getSymbol().equals( itemSymbol ) )
            {
                return tempItem;
            }
        }        
        return null;
    }

    public void printItems() {
        Set set= m_items.entrySet();
        Iterator setIterator= set.iterator();
        
        while( setIterator.hasNext() )
        {
            Map.Entry me= (Map.Entry) setIterator.next();
            Item tempItem= (Item) me.getKey();
            System.out.println( tempItem.getName() + " (" + tempItem.getSymbol() + ") (Qty: " + me.getValue() + ")" );
        }
    }

    public void useItem( String userItem )
    {
        // Character has the item, we can use it!
        Item tempItem= getItemFromSymbol( userItem );
        HashMap<ItemEffects,Integer> itemEffects;

        if( tempItem!=null )
        {
            itemEffects= tempItem.use();
            if( itemEffects!=null )
            {
                // Check item effects
                if( itemEffects.containsKey( ItemEffects.INCREASE_HP ) )
                {
                    this.changeHP(itemEffects.get(ItemEffects.INCREASE_HP));
                    System.out.println( this.getName() + "'s HP has increased to " + this.getHP() );

                    if( tempItem.getType().equals( ItemType.ONCE_ONLY ) )
                    {
                        // Need to remove it from the character's stash!
                        int value= m_items.get( tempItem ) - 1;
                        if( value==0 )
                        {
                            // need to remove the item reference from the characters stash!
                            m_items.remove( tempItem );
                        }
                        else
                        {
                            // else, put the new updated value!
                            m_items.put( tempItem, value );
                        }
                    }
                }
                //TODO: complete this massive if statement! lol.
            }
        }
        else
        {
            System.out.println( "The Character does not have the item!" );
        }

        //TODO: need to deduct the quantity OR remove the item from the HashMap
    }
}
