package dev.vladimir.models.baseComponents.drawable;

import dev.vladimir.MainWindow;
import dev.vladimir.models.baseComponents.logic.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Drawable {
    private final Image texture;

    Drawable(String textureName) {
        this.texture = loadTexture(textureName);
    }

    Drawable(String textureName, int imageSize) {
        this(textureName, imageSize, imageSize);
    }

    Drawable(String textureName, int imageHeight, int imageWidth) {
        if(imageHeight >= 0 || imageWidth >= 0) {
            this.texture =
                    loadTexture(textureName)
                            .getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
        } else
            throw new RuntimeException("image size can not be less zero");
    }

    public void draw(Graphics g, Point point) {
        g.drawImage(texture, (int) point.x, (int) point.y, null);
    }

    public static Image loadTexture(final String fileName) {
        InputStream ioStream = MainWindow.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new RuntimeException(fileName + " is not found");
        }

        try {
            return ImageIO.read(ioStream);
        } catch (IOException e) {
            System.out.println(fileName + " is not image");
        }
        return null;
    }
}
