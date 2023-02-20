package dev.vladimir.listeners;

import dev.vladimir.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLoopActionListener implements ActionListener {
    public static double lastClipTime = System.currentTimeMillis();
    private final MainWindow mainWindow;

    public MainLoopActionListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.repaint();
        MainWindow.game.repaint();

        setFpsText();
    }

    private static void setFpsText() {
        double timeLastClip = System.currentTimeMillis() - lastClipTime;

        if(timeLastClip != 0) {
            int framePerSeconds = (int) (1000 / timeLastClip);
            MainWindow.fpsText.setText(String.valueOf(framePerSeconds));
        }
        lastClipTime = System.currentTimeMillis();
    }
}
