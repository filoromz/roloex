package main.item;

import main.GUI;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Rommel
 * Date: 1/01/12
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class DummyItem extends Item {
    private static final String ITEM_NAME= "Dummy Item";
    private static final ItemType ITEM_TYPE= ItemType.ONCE_ONLY;
    private static final String ITEM_SYMBOL= "dummy";

    public DummyItem() {
        super( ITEM_NAME, ITEM_TYPE, ITEM_SYMBOL );
    }

    @Override
    public HashMap<ItemEffects, Integer> use() {
        GUI.displayText("DummyItem is used \n");
        return null;
    }
}
