package main.item;

import main.GUI;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 2/01/12
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class PotionItem extends Item {
    private static final String ITEM_NAME= "Red Potion";
    private static final ItemType ITEM_TYPE= ItemType.ONCE_ONLY;
    private static final String ITEM_SYMBOL= "rpot";
    
    private static final HashMap<ItemEffects,Integer> m_effects= new HashMap<ItemEffects, Integer>()
    {
        {
            put( ItemEffects.HP, 5 );
        }
    };
            
    public PotionItem() {
        super( ITEM_NAME, ITEM_TYPE, ITEM_SYMBOL );
    }

    @Override
    public HashMap<ItemEffects,Integer> use() {
        GUI.displayText("Red Potion is used \n");
        return m_effects;
    }
}
