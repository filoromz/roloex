package main.item;

import main.GUI;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 31/12/11
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeartOfOfferingItem extends Item {
    // There should only be one instance of this Item!
    private static final String ITEM_NAME= "Heart Of Offering";
    private static final ItemType ITEM_TYPE= ItemType.ONCE_ONLY;
    private static final String ITEM_SYMBOL= "hoo";

    private static HeartOfOfferingItem ourInstance= new HeartOfOfferingItem();

    public static HeartOfOfferingItem getInstance() {
        return ourInstance;
    }

    private HeartOfOfferingItem() {
        super( ITEM_NAME, ITEM_TYPE, ITEM_SYMBOL );
    }

    @Override
    public HashMap<ItemEffects, Integer> use() {
        // This is a once only item
        GUI.displayText("Congratulations! You have won! Thanks for playing!\n");
        return null;
    }
}
