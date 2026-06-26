package ch.bbw.gamebbwoy;

import ch.bbw.gamebbwoy.api.PixelDisplay;
import ch.bbw.gamebbwoy.example.PixelSprite;

import java.util.ArrayList;

public class Obstacle {

    private static final int[][] ARRAY_PIPE = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 1, 1, 1, 1, 1, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
    };

    private static final int SPRITE_WIDTH = 25;
    private static final int SPRITE_HEIGHT = 25;
    private static double SPEED = 0.8;

    public static void increaseSpeed() {
        SPEED += 0.1;
    }

    public static void resetSpeed() {
        SPEED = 0.8;
    }

    private final PixelSprite sprite;

    public Obstacle(PixelDisplay graphic) {
        this(graphic, 0);
    }

    public Obstacle(PixelDisplay graphic, int offsetX) {
        this.sprite = toSprite(ARRAY_PIPE);
        sprite.setX((graphic.getPixelWidth() - SPRITE_WIDTH) + offsetX);
        sprite.setY(graphic.getPixelHeight() - SPRITE_HEIGHT);
    }

    private PixelSprite toSprite(int[][] array) {
        var flat = new ArrayList<Integer>();

        for (var col = 0; col < SPRITE_WIDTH; col++) {
            for (var row = 0; row < SPRITE_HEIGHT; row++) {
                flat.add(array[row][col]);
            }
        }

        return new PixelSprite(flat, SPRITE_WIDTH, SPRITE_HEIGHT);
    }

    public void tick(PixelDisplay graphic) {
        sprite.setX(sprite.getX() - SPEED);

        if (sprite.getX() + SPRITE_WIDTH <= graphic.getPixelWidth() && sprite.getX() + SPRITE_WIDTH > 0) {
            sprite.tick(graphic);
        }
    }

    public boolean isOffScreen() {
        return sprite.getX() < 0;
    }

    public double getX() {
        return sprite.getX();
    }

    public double getY() {
        return sprite.getY();
    }
}