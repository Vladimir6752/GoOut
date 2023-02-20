package dev.vladimir;

import dev.vladimir.listeners.KeyListener;
import dev.vladimir.listeners.MouseClickListenerImpl;
import dev.vladimir.models.baseComponents.drawable.Sprites;
import dev.vladimir.visual.FpsText;
import dev.vladimir.visual.Game;

import javax.swing.*;

public class MainWindow extends JFrame {
    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 900;
    public static final String WINDOW_TITLE = "GoOut";
    public static Game game;
    public static FpsText fpsText;

    public static void main(String[] args) {
        new MainWindow();
    }

    public MainWindow() {
        setDefaultSettings();
        Sprites.init();

        add(fpsText = new FpsText());
        add(game = new Game(this));
        addKeyListener(new KeyListener());
        addMouseListener(new MouseClickListenerImpl());

        //setFullScreenMode();
        setVisible(true);
    }

    private void setDefaultSettings() {
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(1920 / 2 - WINDOW_WIDTH / 2, 1080 / 2 - WINDOW_HEIGHT / 2);
    }

    private void setFullScreenMode() {
        /*GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(this);*/

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }
}