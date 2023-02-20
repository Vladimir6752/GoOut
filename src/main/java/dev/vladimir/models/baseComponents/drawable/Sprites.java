package dev.vladimir.models.baseComponents.drawable;

import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Block;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Item;

import java.util.Map;

public class Sprites {
    private static Map<String, Drawable> blockSpritesMap;
    private static Map<String, Drawable> entitySpritesMap;
    private static Map<String, Drawable> itemsSpritesMap;

    public static void init() {
        blockSpritesMap = Map.ofEntries(
                Map.entry("diamondOre", new Drawable("sprites/blocks/diamondOre.png", Block.BLOCK_SIZE)),
                Map.entry("goldOre", new Drawable("sprites/blocks/goldOre.png", Block.BLOCK_SIZE)),
                Map.entry("cobble", new Drawable("sprites/blocks/cobble.png", Block.BLOCK_SIZE))
        );

        itemsSpritesMap = Map.ofEntries(
                Map.entry("diamondOre", new Drawable("sprites/blocks/diamondOre.png", Item.ITEM_SIZE)),
                Map.entry("goldOre", new Drawable("sprites/blocks/goldOre.png", Item.ITEM_SIZE)),
                Map.entry("cobble", new Drawable("sprites/blocks/cobble.png", Item.ITEM_SIZE)),
                Map.entry("pickaxe", new Drawable("sprites/items/pickaxe.png", Item.ITEM_SIZE))
        );

        entitySpritesMap = Map.ofEntries(
                Map.entry("player-run-r1", new Drawable("sprites/enemies/player/run/run-r-1.png")),
                Map.entry("player-run-r2", new Drawable("sprites/enemies/player/run/run-r-2.png")),
                Map.entry("player-run-r3", new Drawable("sprites/enemies/player/run/run-r-3.png")),

                Map.entry("player-run-l1", new Drawable("sprites/enemies/player/run/run-l-1.png")),
                Map.entry("player-run-l2", new Drawable("sprites/enemies/player/run/run-l-2.png")),
                Map.entry("player-run-l3", new Drawable("sprites/enemies/player/run/run-l-3.png")),

                Map.entry("player-stay-1", new Drawable("sprites/enemies/player/stay/stay-1.png")),
                Map.entry("player-stay-2", new Drawable("sprites/enemies/player/stay/stay-2.png")),
                Map.entry("player-stay-3", new Drawable("sprites/enemies/player/stay/stay-3.png"))
        );
    }

    public static Drawable blockSpriteOf(String key) {
        return blockSpritesMap.get(key);
    }

    public static Drawable itemSpriteOf(String key) {
        return itemsSpritesMap.get(key);
    }

    public static Drawable entitySpriteOf(String key) {
        return entitySpritesMap.get(key);
    }
}
