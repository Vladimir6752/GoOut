package dev.vladimir.visual;

import dev.vladimir.MainWindow;
import dev.vladimir.listeners.MainLoopActionListener;
import dev.vladimir.models.baseComponents.world.GameMap;
import dev.vladimir.models.baseComponents.logic.Point;
import dev.vladimir.models.enemies.Player;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    public static Player player = new Player(new Point(750, 400));
    public static GameMap gameMap = new GameMap(new Point(-1000, -1000));
    public static MainWindow mainWindow;

    public Game(MainWindow mainWindow) {
        Game.mainWindow = mainWindow;
        new Timer(0, new MainLoopActionListener(mainWindow)).start();

        setBackground(Color.gray);
        setBounds(0,0, MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameMap.draw(g);
        player.draw(g);
    }
}
