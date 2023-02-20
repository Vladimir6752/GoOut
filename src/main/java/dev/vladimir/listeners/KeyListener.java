package dev.vladimir.listeners;

import dev.vladimir.models.baseComponents.logic.Point;
import dev.vladimir.visual.Game;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S -> Game.player.move(new Point(0, -1));
            case KeyEvent.VK_W -> Game.player.move(new Point(0, 1));
            case KeyEvent.VK_A -> Game.player.move(new Point(1, 0));
            case KeyEvent.VK_D -> Game.player.move(new Point(-1, 0));
            case KeyEvent.VK_E -> {
                Game.player.inventory.setShowOrOpen();
                Game.player.setStayState();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case
                    KeyEvent.VK_W,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_S
                    -> Game.player.setStayState();
        }
    }
}
