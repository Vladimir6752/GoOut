package dev.vladimir.models.baseComponents.gameObjects.inheritObject;

import dev.vladimir.models.baseComponents.drawable.Drawable;
import dev.vladimir.models.baseComponents.drawable.Sprites;
import dev.vladimir.models.baseComponents.gameObjects.GameObject;

public class Item extends GameObject {
    public final int inventoryWeight;
    public static final int ITEM_SIZE = 32;
    public Drawable itemSprite;
    public final String spriteName;

    public Item(int inventoryWeight, String spriteName) {
        this.inventoryWeight = inventoryWeight;
        itemSprite = Sprites.itemSpriteOf(spriteName);
        this.spriteName = spriteName;
    }
}
