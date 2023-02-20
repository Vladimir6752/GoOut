package dev.vladimir.models.baseComponents.gameObjects;

import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Block;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Item;
import dev.vladimir.models.baseComponents.logic.ItemStack;
import dev.vladimir.models.baseComponents.logic.Point;
import dev.vladimir.models.baseComponents.world.ChunkBlocks;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.Objects;

public enum GameObjectType {
    NULL(new Block(ItemStack.MAX_ITEMS_WEIGHT_IN_SLOT, "null") {
        @Override
        public void draw(Graphics g) {}
    }),
    COBBLESTONE(new Block(1, "cobble")),
    GOLD_ORE(new Block(1, "goldOre")),
    DIAMOND_ORE(new Block(1, "diamondOre")),
    PICKAXE(new Item(999, "pickaxe"));

    private final GameObject gameObject;

    GameObjectType(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Item getItem() {
        return (Item) gameObject;
    }

    public Block getBlock() {
        return (Block) gameObject;
    }

    public Block getNewBlock(dev.vladimir.models.baseComponents.logic.Point localPosition, Point indexPositionInChunk, boolean isInit, ChunkBlocks thisChunk) {
        Block block = getBlock();
        Class<? extends Block> blockClass = block.getClass();

        Constructor<? extends Block> declaredConstructor;

        try {
            declaredConstructor = blockClass.getDeclaredConstructor(int.class, String.class, Point.class, Point.class, boolean.class, ChunkBlocks.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        try {
            return declaredConstructor.newInstance(block.inventoryWeight, block.spriteName, localPosition, indexPositionInChunk, isInit, thisChunk);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static GameObjectType ofBlock(Block block) {
        for (GameObjectType x : values()) {
            if (x.gameObject instanceof Block && Objects.equals(x.getBlock().spriteName, block.spriteName)) {
                return x;
            }
        }
        return NULL;
    }
}
