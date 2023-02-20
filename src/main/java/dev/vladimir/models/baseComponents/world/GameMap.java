package dev.vladimir.models.baseComponents.world;

import dev.vladimir.models.baseComponents.logic.Point;
import dev.vladimir.models.enemies.Player;
import dev.vladimir.visual.Game;

import java.awt.*;
import java.util.function.Consumer;

public class GameMap {
    public static Point position;
    private final ChunkBlocks[][] chunks = new ChunkBlocks[150][150];

    public GameMap(Point position) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println((runtime.totalMemory() - runtime.freeMemory()) / 1000000);
        System.out.println();

        BlockGenerator.init();
        GameMap.position = position;
        for (int x = 0; x < chunks.length; x++) {
            for (int y = 0; y < chunks[x].length; y++) {
                chunks[x][y] = new ChunkBlocks(
                        new Point(
                                x * ChunkBlocks.CHUNK_SIZE,
                                y * ChunkBlocks.CHUNK_SIZE
                        )
                );
            }
        }
        System.out.println((runtime.totalMemory() - runtime.freeMemory()) / 1000000);
        System.out.println();
        debugMapInit();
    }

    private void debugMapInit() {
        int chunksAmount = chunks.length * chunks[1].length;
        int blocksAmount = chunksAmount * ChunkBlocks.CHUNK_BLOCKS_SIZE * ChunkBlocks.CHUNK_BLOCKS_SIZE;
        System.out.println(
                "map initialized with " + '\n' +
                        " chunks: " +
                        chunksAmount + '\n' +
                        " blocks: " +
                        blocksAmount
                );
    }

    public void draw(Graphics g) {
        forEachChunkBlocks(chunkBlock -> drawChunk(g, chunkBlock));

        Player.drawCursorBlock(g);
    }

    private static void drawChunk(Graphics g, ChunkBlocks chunkBlock) {
        Point globalPosition = new Point(
                chunkBlock.localPosition.x + GameMap.position.x,
                chunkBlock.localPosition.y + GameMap.position.y
        );

        if(globalPosition.distanceToPoint(Game.player.position) < ChunkBlocks.CHUNK_SIZE * Player.fovChunks) {
            chunkBlock.drawChunk(g);
        }
    }

    public void forEachChunkBlocks(Consumer<ChunkBlocks> consumer) {
        for (ChunkBlocks[] chunk : chunks) {
            for (ChunkBlocks chunkBlocks : chunk) {
                consumer.accept(chunkBlocks);
            }
        }
    }

    public ChunkBlocks getChunkAtPosition(Point positionOnMap) {
        double x = (positionOnMap.x - position.x) / ChunkBlocks.CHUNK_SIZE;
        double y = (positionOnMap.y - position.y) / ChunkBlocks.CHUNK_SIZE;

        if(x > 0 && y > 0 && chunks.length > x && chunks[(int) x].length > y)
            return chunks[(int) x][(int) y];
        else return null;
    }
}
