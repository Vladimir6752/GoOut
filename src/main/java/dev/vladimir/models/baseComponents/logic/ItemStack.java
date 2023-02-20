package dev.vladimir.models.baseComponents.logic;

import dev.vladimir.models.baseComponents.gameObjects.GameObjectType;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Item;

public class ItemStack {
    public static final int MAX_ITEMS_WEIGHT_IN_SLOT = 99;
    private GameObjectType currentGameObjectType = GameObjectType.NULL;
    private int itemsWeight = 0;

    public void addItem(GameObjectType gameObjectType) {
        int addedWeight = gameObjectType.getItem().inventoryWeight;

        if(isNull()) {
            setItem(gameObjectType);
            return;
        }

        if(currentGameObjectType == gameObjectType && itemsWeight + addedWeight <= MAX_ITEMS_WEIGHT_IN_SLOT) {
            itemsWeight = itemsWeight + addedWeight;
        } else throw new RuntimeException("error code");
    }

    public void setItem(GameObjectType gameObjectType) {
        currentGameObjectType = gameObjectType;
        itemsWeight = gameObjectType.getItem().inventoryWeight;
    }

    public int getItemsAmount() {
        try {
            return itemsWeight / currentGameObjectType.getItem().inventoryWeight;
        }  catch (Exception ignored) {
            return 0;
        }
    }

    public boolean isFull() {
        return itemsWeight >= MAX_ITEMS_WEIGHT_IN_SLOT;
    }

    public boolean isNull() {
        return currentGameObjectType == GameObjectType.NULL;
    }

    public boolean isNot(GameObjectType gameObjectType) {
        return !(currentGameObjectType == gameObjectType);
    }

    public Item getItem() {
        return currentGameObjectType.getItem();
    }

    public GameObjectType takeItem() {
        GameObjectType resultType = GameObjectType.NULL;

        if(getItemsAmount() == 1) {
            resultType = GameObjectType.valueOf(currentGameObjectType.name());

            setItem(GameObjectType.NULL);
        }

        return resultType;
    }
}