package dev.vladimir.models.baseComponents.logic;

import dev.vladimir.models.baseComponents.gameObjects.GameObjectType;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Block;
import dev.vladimir.models.enemies.Player;
import dev.vladimir.visual.Game;

public class Cursor {

    public static void onLeftClick() {
        /*if (Game.player.takeItemInInventory() == MethodResultType.Confirm)
            return;*/


        if (!Game.player.inventory.isClose && Game.player.inventorySlotUnderCursor != null) {

        }

        Game.player.destroyBlockUnderCursor();
    }

    public static void onRightClick() {
        Block cursorBlock = Player.blockUnderCursor;
        if(cursorBlock != null) {
            Player.chunkUnderCursor.setBlock(
                    cursorBlock.indexPositionInChunk,
                    GameObjectType.COBBLESTONE.getNewBlock(
                            cursorBlock.localPosition,
                            cursorBlock.indexPositionInChunk,
                            false,
                            Player.chunkUnderCursor
                    )
                    /*new Block(
                            GameObjectType.COBBLESTONE.getBlock(),
                            cursorBlock.localPosition,
                            cursorBlock.indexPositionInChunk,
                            false,
                            Player.chunkUnderCursor
                    )*/
            );
        }
    }

    public enum MethodResultType {
        Confirm, Error
    }
}