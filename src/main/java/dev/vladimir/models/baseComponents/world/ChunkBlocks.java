package dev.vladimir.models.baseComponents.world;

import dev.vladimir.models.baseComponents.gameObjects.GameObjectType;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Block;
import dev.vladimir.models.baseComponents.logic.Point;

import java.awt.*;
import java.util.function.Consumer;

public class ChunkBlocks {
    public static final int CHUNK_BLOCKS_SIZE = 8;
    public static final int CHUNK_SIZE = CHUNK_BLOCKS_SIZE * Block.BLOCK_SIZE;
    public final Block[][] blocks = new Block[CHUNK_BLOCKS_SIZE][CHUNK_BLOCKS_SIZE];
    public final Point localPosition;

    public ChunkBlocks(Point localPosition) {
        this.localPosition = localPosition;
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y] = getRandomBlock(localPosition, x, y);
            }
        }
    }

    public void setBlock(Point index,Block block) {
        blocks[(int) index.x][(int) index.y] = block;
    }

    public void drawChunk(Graphics g) {
        forEachBlocks(block -> block.draw(g));
    }

    private Block getRandomBlock(Point localPosition, int x, int y) {
        try {
            return GameObjectType
                    .ofBlock(BlockGenerator.getRandomBlock())
                    .getNewBlock(
                        new Point(x * Block.BLOCK_SIZE + localPosition.x, y * Block.BLOCK_SIZE + localPosition.y),
                        new Point(x, y),
                        true,
                        this
            );
                            /*,
                            new Point(x * Block.BLOCK_SIZE + localPosition.x, y * Block.BLOCK_SIZE + localPosition.y),
                            new Point(x, y),
                            true,
                            this
                    );*/
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void forEachBlocks(Consumer<Block> consumer) {
        for (Block[] blockX : blocks) {
            for (Block blockY : blockX) {
                if(blockY != null)
                    consumer.accept(blockY);
            }
        }
    }

    public Block getBlockAtPosition(Point positionOnChunk) {
        Point globalPosition = new Point(localPosition.x + GameMap.position.x, localPosition.y + GameMap.position.y);
        double x = (positionOnChunk.x - globalPosition.x) / Block.BLOCK_SIZE;
        double y = (positionOnChunk.y - globalPosition.y) / Block.BLOCK_SIZE;

        if(x > 0 && y > 0 && blocks.length > x && blocks[(int) x].length > y)
            return blocks[(int) x][(int) y];
        else return null;
    }
}
