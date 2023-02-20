package dev.vladimir.models.baseComponents.logic;

import dev.vladimir.models.baseComponents.gameObjects.GameObjectType;
import dev.vladimir.models.baseComponents.gameObjects.inheritObject.Item;

import java.awt.*;

public class Inventory {
    private final Color backgroundColor = Color.lightGray;
    private final Color inventoryCellColor = Color.gray;
    public InventorySlot[][] inventorySlots;
    private final InventorySlot activeSlot;
    public final Point positionOnScreen;
    public final int borderRadius = 35;
    public final int cellSize = 64;
    public final int cellGap = 16;
    public boolean isClose = true;
    private final int height;
    private final int width;

    public Inventory(int widthAmount, int heightAmount, Point positionOnScreen) {
        this.positionOnScreen = positionOnScreen;
        setInventorySlots(widthAmount, heightAmount);
        activeSlot = inventorySlots[0][0];

        height = heightAmount * (cellGap + cellSize) + cellGap;
        width = widthAmount * (cellGap + cellSize) + cellGap;
    }

    public void draw(Graphics g) {
        drawShowingInventory(g);
        drawClosingInventory(g);
        drawActiveSlot(g);
    }

    private void drawActiveSlot(Graphics g) {
        int margin = 6;

        g.setColor(Color.yellow);
        g.drawRoundRect(
                (int) (positionOnScreen.x
                        + activeSlot.indexInInventory.x
                        * (cellGap + cellSize)
                        + cellGap - margin / 2),
                (int) (positionOnScreen.y + cellGap - margin / 2),
                cellSize + margin,
                cellSize + margin,
                borderRadius + margin,
                borderRadius + margin
        );
    }

    public void addItem(GameObjectType gameObjectType) {
        for (InventorySlot[] inventorySlotY : inventorySlots) {
            for (InventorySlot slot : inventorySlotY) {
                if(!slot.isNull()) {
                    if (slot.isFull() || slot.isNot(gameObjectType)) continue;
                }

                slot.addItem(gameObjectType);
                return;
            }
        }
    }

    private void drawClosingInventory(Graphics g) {
        if(isClose) return;

        drawBackground(g);
        drawCells(g);
        drawItems(g);
    }

    private void setInventorySlots(int widthAmount, int heightAmount) {
        inventorySlots = new InventorySlot[heightAmount][widthAmount];

        for (int j = 0; j < inventorySlots.length; j++) {
            for (int i = 0; i < inventorySlots[j].length; i++) {
                inventorySlots[j][i] = new InventorySlot(new Point(i , j));
            }
        }
    }

    private void drawShowingInventory(Graphics g) {
        InventorySlot[] showingSlots = inventorySlots[0];

        drawShowingBackground(g, showingSlots);

        for (int x = 0; x < showingSlots.length; x++) {
            g.setColor(inventoryCellColor);
            InventorySlot slot = showingSlots[x];

            drawShowingCell(g, x);

            slot.draw(
                    g,
                    new Point(
                            x * (cellSize + cellGap) + (cellSize / 2f - Item.ITEM_SIZE / 2f) + cellGap + positionOnScreen.x,
                            cellSize / 2f - Item.ITEM_SIZE / 2f + cellGap + positionOnScreen.y
                    )
            );
        }
    }

    private void drawShowingCell(Graphics g, int x) {
        g.fillRoundRect(
                (int) (x * (cellSize + cellGap) + cellGap + positionOnScreen.x),
                (int) (cellGap + positionOnScreen.y),
                cellSize,
                cellSize,
                borderRadius,
                borderRadius
        );
    }

    private void drawShowingBackground(Graphics g, InventorySlot[] showingSlots) {
        g.setColor(backgroundColor);
        g.fillRoundRect(
                (int) positionOnScreen.x,
                (int) positionOnScreen.y,
                showingSlots.length * (cellGap + cellSize) + cellGap,
                cellGap * 2 + cellSize,
                borderRadius,
                borderRadius
        );
    }

    private void drawBackground(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRoundRect(
                (int) positionOnScreen.x,
                (int) positionOnScreen.y,
                width,
                height,
                borderRadius,
                borderRadius
        );
    }

    private void drawCells(Graphics g) {
        for (int y = 0; y < inventorySlots.length; y++) {
            InventorySlot[] inventorySlot = inventorySlots[y];

            for (int x = 0; x < inventorySlot.length; x++) {
                g.setColor(inventoryCellColor);
                g.fillRoundRect(
                        (int) (x * (cellSize + cellGap) + cellGap + positionOnScreen.x),
                        (int) (y * (cellSize + cellGap) + cellGap + positionOnScreen.y),
                        cellSize,
                        cellSize,
                        borderRadius,
                        borderRadius
                );
            }
        }
    }

    private void drawItems(Graphics g) {
        for (int y = 0; y < inventorySlots.length; y++) {
            InventorySlot[] inventorySlotY = inventorySlots[y];
            for (int x = 0; x < inventorySlotY.length; x++) {
                InventorySlot slot = inventorySlotY[x];
                slot.draw(
                        g,
                        new Point(
                                x * (cellSize + cellGap) + (cellSize / 2f - Item.ITEM_SIZE / 2f) + cellGap + positionOnScreen.x,
                                y * (cellSize + cellGap) + (cellSize / 2f - Item.ITEM_SIZE / 2f) + cellGap + positionOnScreen.y
                        )
                );
            }
        }
    }

    public void setShowOrOpen() {
        isClose = !isClose;
    }
}