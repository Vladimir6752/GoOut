package dev.vladimir.models.baseComponents.drawable;

import dev.vladimir.models.baseComponents.logic.Point;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Animation {
    private final Queue<Drawable> sprites = new LinkedList<>();
    private Drawable currentSprite;
    private final double animationDelay;
    private double lastCallAnimation;
    private double spriteTimer;

    public Animation(double animationDelay, Drawable... sprites) {
        setQueue(sprites);
        currentSprite = this.sprites.peek();
        this.animationDelay = animationDelay * 1000;
        lastCallAnimation = System.currentTimeMillis();
    }

    private Drawable getCurrentDrawable() {
        if(spriteTimer < animationDelay) {
            spriteTimer += System.currentTimeMillis() - lastCallAnimation;
        } else {
            spriteTimer = 0;
            sprites.offer(currentSprite = sprites.poll());
        }

        lastCallAnimation = System.currentTimeMillis();

        return currentSprite;
    }

    private void setQueue(Drawable[] sprites) {
        Iterator<Drawable> iterator = Arrays.stream(sprites).iterator();
        iterator.forEachRemaining(this.sprites::offer);
    }

    public void draw(Graphics g, Point point) {
        getCurrentDrawable().draw(g, point);
    }
}
