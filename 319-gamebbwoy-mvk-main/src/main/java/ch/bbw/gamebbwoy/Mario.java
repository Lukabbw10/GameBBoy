package ch.bbw.gamebbwoy;

import ch.bbw.gamebbwoy.api.PixelDisplay;
import ch.bbw.gamebbwoy.example.PixelSprite;

import java.util.ArrayList;

public class Mario {

    private static final int[][] ARRAY_STAND = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 1, 1, 1, 0, 3, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 1, 0, 1, 0, 0, 3, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 1, 1, 0, 0, 0, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 2, 2, 1, 2, 1, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 2, 2, 2, 1, 2, 1, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 2, 2, 2, 2, 3, 1, 3, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 0, 0, 2, 1, 1, 1, 1, 1, 2, 0, 0, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 1, 1, 1, 4, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 3, 4, 4, 4, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 3, 3, 3, 4, 4, 4, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
    };

    private static final int[][] ARRAY_WALK = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 1, 1, 1, 0, 3, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 1, 0, 1, 0, 0, 3, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 1, 1, 0, 0, 0, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 2, 2, 1, 2, 1, 2, 2, 2, 0, 0, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 2, 2, 2, 1, 2, 1, 2, 2, 2, 0, 0, 4, 4, 4, 4, 4, 4},
            {4, 4, 2, 2, 2, 2, 3, 1, 3, 2, 2, 0, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 2, 2, 4, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 0, 0, 2, 4, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 0, 0, 4, 1, 1, 1, 1, 1, 1, 1, 4, 3, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 3, 3, 1, 1, 1, 4, 1, 1, 1, 3, 3, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 3, 3, 1, 4, 4, 4, 4, 4, 1, 3, 3, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
    };

    private final PixelSprite frameStand;
    private final PixelSprite frameWalk;
    private final int groundY;
    private double yVelocity = 0;
    private boolean isOnGround = true;
    private int animationTick = 0;
    private boolean showWalkFrame = false;

    public Mario(PixelDisplay graphic) {
        this.frameStand = toSprite(ARRAY_STAND);
        this.frameWalk = toSprite(ARRAY_WALK);

        this.groundY = graphic.getPixelHeight() - 20;

        frameStand.setX(10);
        frameStand.setY(groundY);
        frameWalk.setX(10);
        frameWalk.setY(groundY);
    }

    private PixelSprite toSprite(int[][] marioArray) {
        var flat = new ArrayList<Integer>();

        for (var col = 0; col < 20; col++) {
            for (var row = 0; row < 20; row++) {
                flat.add(marioArray[row][col]);
            }
        }

        return new PixelSprite(flat, 20, 20);
    }

    public void jump() {
        if (isOnGround) {
            yVelocity = -2.3;
            isOnGround = false;
        }
    }

    public void tick(PixelDisplay graphic) {
        yVelocity += 0.08;

        var nextY = frameStand.getY() + yVelocity;

        if (nextY >= groundY) {
            nextY = groundY;
            yVelocity = 0;
            isOnGround = true;
        }

        frameStand.setY(nextY);
        frameWalk.setY(nextY);

        animationTick++;
        if (animationTick >= 8) {
            animationTick = 0;
            showWalkFrame = !showWalkFrame;
        }

        if (!isOnGround || !showWalkFrame) {
            frameStand.tick(graphic);
        } else {
            frameWalk.tick(graphic);
        }
    }
    public double getY() {
        return frameStand.getY();
    }
}