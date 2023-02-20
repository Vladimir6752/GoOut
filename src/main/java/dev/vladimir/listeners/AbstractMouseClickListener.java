package dev.vladimir.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class AbstractMouseClickListener implements MouseListener {
    public void leftClick(MouseEvent e) {}

    public void rightClick(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftClick(e);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightClick(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
