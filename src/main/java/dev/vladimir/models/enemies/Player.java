package dev.vladimir.models.enemies;

import dev.vladimir.models.baseComponents.gameObjects.GameObjectType;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Block;
import dev.vladimir.models.baseComponents.drawable.Animation;
import dev.vladimir.models.baseComponents.drawable.AnimationState;
import dev.vladimir.models.baseComponents.drawable.Sprites;
import dev.vladimir.models.baseComponents.logic.Inventory;
import dev.vladimir.models.baseComponents.logic.InventorySlot;
import dev.vladimir.models.baseComponents.logic.Point;
import dev.vladimir.models.baseComponents.world.ChunkBlocks;
import dev.vladimir.models.baseComponents.world.GameMap;
import dev.vladimir.visual.Game;

import java.awt.*;
import java.util.Map;

public class Player {
    private final AnimationState animationState = getPlayerAnimations();
    public Inventory inventory = new Inventory(9, 4, new Point(16, 16));
    public static int fovChunks = 3;
    public Point position;
    public static ChunkBlocks chunkUnderCursor;
    public static Block blockUnderCursor;
    public InventorySlot inventorySlotUnderCursor;

    public Player(Point position) {
        this.position = position;
        setStayState();
    }

    public void draw(Graphics g) {
        animationState.drawAnimation(g, position);

        inventory.draw(g);

        if(inventory.isClose) {
            updateBlockUnderCursor();
        } else {
            updateItemUnderCursor();
        }
    }

    private void updateItemUnderCursor() {

    }

    private void updateBlockUnderCursor() {
        java.awt.Point location = MouseInfo.getPointerInfo().getLocation();

        Point positionOnMap = new Point(location.x - 168, location.y - 120);
        chunkUnderCursor = Game.gameMap.getChunkAtPosition(positionOnMap);

        if(chunkUnderCursor != null)
            blockUnderCursor = chunkUnderCursor.getBlockAtPosition(positionOnMap);
    }

    public static void drawCursorBlock(Graphics g) {
        if(blockUnderCursor != null) {
            Point blockGlobalPosition = new Point(
                    blockUnderCursor.localPosition.x + GameMap.position.x,
                    blockUnderCursor.localPosition.y + GameMap.position.y
            );
            g.setColor(Color.yellow);
            ((Graphics2D) g).setStroke(new BasicStroke(5));
            g.drawRect(
                    (int) blockGlobalPosition.x,
                    (int) blockGlobalPosition.y,
                    Block.BLOCK_SIZE,
                    Block.BLOCK_SIZE
            );
        }
    }

    public void move(Point point) {
        Point direction = point.multiply(5);
        GameMap.position.add(direction);
        setRunState(direction.x < 0 || direction.y > 0);
    }

    private void setRunState(boolean isRight) {
        animationState.setAnimation(
                isRight ?
                        AnimationState.RUN_RIGHT_ANIMATION_NAME
                        :
                        AnimationState.RUN_LEFT_ANIMATION_NAME
        );
    }

    public void setStayState() {
        animationState.setAnimation(AnimationState.STAY_ANIMATION_NAME);
    }

    private static AnimationState getPlayerAnimations() {
        return new AnimationState(Map.ofEntries(
                Map.entry(AnimationState.STAY_ANIMATION_NAME,
                        new Animation(0.35,
                                Sprites.entitySpriteOf("player-stay-1"),
                                Sprites.entitySpriteOf("player-stay-2"),
                                Sprites.entitySpriteOf("player-stay-3"),
                                Sprites.entitySpriteOf("player-stay-2")
                        )
                ),
                Map.entry(AnimationState.RUN_LEFT_ANIMATION_NAME,
                        new Animation(0.2,
                                Sprites.entitySpriteOf("player-run-l1"),
                                Sprites.entitySpriteOf("player-run-l2"),
                                Sprites.entitySpriteOf("player-run-l3")
                        )
                ),
                Map.entry(AnimationState.RUN_RIGHT_ANIMATION_NAME,
                        new Animation(0.2,
                                Sprites.entitySpriteOf("player-run-r3"),
                                Sprites.entitySpriteOf("player-run-r2"),
                                Sprites.entitySpriteOf("player-run-r1")
                        )
                )
        ));
    }

    public void destroyBlockUnderCursor() {
        if(blockUnderCursor != null && inventory.isClose) {
            blockUnderCursor.destroy();
            inventory.addItem(GameObjectType.ofBlock(Player.blockUnderCursor));
        }
    }
}
