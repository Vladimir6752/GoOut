package dev.vladimir.models.baseComponents.gameObjects.inheritObject;

import dev.vladimir.models.baseComponents.drawable.Drawable;
import dev.vladimir.models.baseComponents.drawable.Sprites;
import dev.vladimir.models.baseComponents.gameObjects.GameObjectType;
import dev.vladimir.models.baseComponents.logic.Point;
import dev.vladimir.models.baseComponents.world.ChunkBlocks;
import dev.vladimir.models.baseComponents.world.GameMap;

import java.awt.*;

public class Block extends Item {
    public Point indexPositionInChunk;
    public ChunkBlocks thisChunk;
    public static final int BLOCK_SIZE = 64;
    public Point localPosition;
    public Drawable blockSprite;

    public Block(int inventoryWeight, String spriteName, Point localPosition, Point indexPositionInChunk, boolean isInit, ChunkBlocks thisChunk) {
        super(inventoryWeight, spriteName);

        if(!spriteName.equals("null"))
            blockSprite = Sprites.blockSpriteOf(spriteName);

        this.localPosition = localPosition;
        this.indexPositionInChunk = indexPositionInChunk;
        this.thisChunk = thisChunk;

        if(!isInit) {
            onInstall();
        }
    }

    public Block(int inventoryWeight, String spriteName) {
        super(inventoryWeight, spriteName);
        /*}*/
        /*if(!spriteName.equals("null")) {
         */
    }

    public void draw(Graphics g) {
        blockSprite.draw(g, new Point(localPosition.x + GameMap.position.x, localPosition.y + GameMap.position.y));
    }

    public void onDestroy() {}

    public void onInstall() {}

    public void destroy() {
        onDestroy();

        Block item = GameObjectType.NULL.getNewBlock(localPosition, indexPositionInChunk, false, thisChunk);
        thisChunk.setBlock(
                indexPositionInChunk,
                /*new Block(item, )*/item
        );
    }
}
