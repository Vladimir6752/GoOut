package dev.vladimir.listeners;

import dev.vladimir.models.baseComponents.logic.Cursor;

import java.awt.event.MouseEvent;

public class MouseClickListenerImpl extends AbstractMouseClickListener {
    @Override
    public void leftClick(MouseEvent e) {
        Cursor.onLeftClick();
    }

    @Override
    public void rightClick(MouseEvent e) {
        Cursor.onRightClick();
    }

}
