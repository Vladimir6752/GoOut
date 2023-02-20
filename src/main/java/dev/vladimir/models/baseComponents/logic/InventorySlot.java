package dev.vladimir.models.baseComponents.logic;

import dev.vladimir.models.baseComponents.drawable.Drawable;
import dev.vladimir.models.baseComponents.gameObjects.GameObjectType;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Item;

import java.awt.*;

public class InventorySlot {
    public final Point indexInInventory;
    public ItemStack itemStack = new ItemStack();

    public InventorySlot(Point index) {
        indexInInventory = index;
    }

    public void draw(Graphics g, Point position) {
        Drawable sprite = getItem().itemSprite;

        if(sprite == null) return;

        drawItem(g, position, sprite);

        drawAmount(g, position);
    }

    public void addItem(GameObjectType gameObjectType) {
        itemStack.addItem(gameObjectType);
    }

    public GameObjectType takeItem() {
        return itemStack.takeItem();
    }

    public ItemStack reverseItem(ItemStack anotherStack) {
        ItemStack tempStack = itemStack;

        itemStack = anotherStack;

        return tempStack;
    }

    public boolean isFull() {
        return itemStack.isFull();
    }

    public boolean isNull() {
        return itemStack.isNull();
    }

    public boolean isNot(GameObjectType gameObjectType) {
        return itemStack.isNot(gameObjectType);
    }

    private Item getItem() {
        return itemStack.getItem();
    }

    private void drawAmount(Graphics g, Point position) {
        g.setColor(Color.black);
        g.drawString(String.valueOf(itemStack.getItemsAmount()), (int) position.x, (int) position.y);
    }

    private void drawItem(Graphics g, Point position, Drawable sprite) {
        sprite.draw(g, position);

        position.add(new Point(Item.ITEM_SIZE / 2f, Item.ITEM_SIZE + 13));
    }
}
