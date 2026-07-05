package ch.bbw.gamebbwoy;

import ch.bbw.gamebbwoy.api.PixelDisplay;
import ch.bbw.gamebbwoy.example.PixelSprite;

import java.util.ArrayList;
import java.util.Random;

public class FlyingGomba {

    private static final int[][] ARRAY_CREATURE = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 3, 3, 3, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 4, 3, 3, 4, 4, 3, 3, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 3, 4, 3, 4, 3, 4, 3, 3, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 3, 4, 3, 4, 3, 4, 3, 4, 3, 2, 3, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 3, 2, 2, 3, 4, 4, 3, 3, 4, 4, 4, 3, 2, 3, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 3, 3, 2, 2, 3, 3, 3, 3, 2, 3, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 0, 3, 3, 3, 3, 2, 2, 2, 2, 3, 3, 3, 3, 2, 3, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 0, 1, 3, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 3, 2, 3, 4, 4, 4, 4},
            {4, 4, 4, 3, 0, 0, 1, 3, 3, 3, 3, 4, 4, 4, 3, 3, 3, 3, 2, 2, 3, 3, 4, 4, 4},
            {4, 4, 4, 3, 0, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 2, 3, 4, 4, 4},
            {4, 3, 3, 0, 1, 1, 3, 4, 3, 3, 4, 4, 3, 3, 4, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4},
            {4, 4, 3, 1, 1, 3, 4, 4, 3, 3, 4, 4, 3, 3, 4, 4, 4, 4, 0, 2, 3, 4, 4, 4, 4},
            {4, 4, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 2, 3, 4, 4, 4, 4},
            {4, 4, 3, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 3, 4, 4, 4, 4, 4},
            {4, 4, 4, 3, 3, 4, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 3, 3, 4, 4, 4, 4, 4},
            {4, 4, 4, 3, 0, 0, 0, 3, 3, 3, 4, 4, 4, 4, 3, 4, 4, 0, 2, 3, 4, 4, 4, 4, 4},
            {4, 4, 4, 3, 2, 2, 0, 0, 4, 4, 3, 3, 3, 3, 4, 4, 0, 0, 2, 3, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 3, 2, 2, 2, 3, 2, 2, 2, 3, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 3, 3, 3, 4, 3, 3, 3, 4, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
    };

    private static final int SPRITE_WIDTH = 25;
    private static final int SPRITE_HEIGHT = 25;
    private static final double SPEED = 1.2;
    private static final int APPEAR_DELAY_TICKS = 200;
    private static final int FLASH_DURATION_TICKS = 15;
    private static final int DIRECTION_CHANGE_TICKS = 60;

    private final Random random = new Random();
    private final PixelSprite sprite;
    private final int displayWidth;
    private final int displayHeight;

    private boolean visible = false;
    private int waitTicks;
    private int directionTicks;
    private double directionX;
    private double directionY;
    private int flashTicksLeft = 0;

    public FlyingGomba(PixelDisplay graphic) {
        this.sprite = toSprite(ARRAY_CREATURE);
        this.displayWidth = graphic.getPixelWidth();
        this.displayHeight = graphic.getPixelHeight();
        this.waitTicks = APPEAR_DELAY_TICKS;
        pickNewDirection();
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

    public void tick(PixelDisplay graphic, int score) {
        if (flashTicksLeft > 0) {
            flashTicksLeft--;
            drawFlash(graphic);
            return;
        }

        if (!visible) {
            if (score >= 13) {
                waitTicks--;
                if (waitTicks <= 0) {
                    spawn();
                }
            }
            return;
        }

        move();
        sprite.tick(graphic);
    }

    private void spawn() {
        visible = true;
        sprite.setX(random.nextInt(displayWidth - SPRITE_WIDTH));
        sprite.setY(random.nextInt(displayHeight - SPRITE_HEIGHT));
        pickNewDirection();
    }

    private void move() {
        directionTicks--;
        if (directionTicks <= 0) {
            pickNewDirection();
        }

        var nextX = sprite.getX() + directionX;
        var nextY = sprite.getY() + directionY;

        if (nextX < 0 || nextX + SPRITE_WIDTH > displayWidth) {
            directionX = -directionX;
            nextX = sprite.getX() + directionX;
        }
        if (nextY < 0 || nextY + SPRITE_HEIGHT > displayHeight) {
            directionY = -directionY;
            nextY = sprite.getY() + directionY;
        }

        sprite.setX(nextX);
        sprite.setY(nextY);
    }

    private void pickNewDirection() {
        var angle = random.nextDouble() * 2 * Math.PI;
        directionX = Math.cos(angle) * SPEED;
        directionY = Math.sin(angle) * SPEED;
        directionTicks = DIRECTION_CHANGE_TICKS;
    }

    private void drawFlash(PixelDisplay graphic) {
        var centerX = (int) sprite.getX() + SPRITE_WIDTH / 2;
        var centerY = (int) sprite.getY() + SPRITE_HEIGHT / 2;
        var radius = (FLASH_DURATION_TICKS - flashTicksLeft) * 2;

        for (var angleDeg = 0; angleDeg < 360; angleDeg += 15) {
            var angleRad = Math.toRadians(angleDeg);
            var px = centerX + (int) (Math.cos(angleRad) * radius);
            var py = centerY + (int) (Math.sin(angleRad) * radius);

            if (px >= 0 && px < displayWidth && py >= 0 && py < displayHeight) {
                graphic.setPixel(px, py, ch.bbw.gamebbwoy.api.PixelColor.BLACK);
            }
        }
    }

    public boolean isColliding(double otherX, double otherY, int otherWidth, int otherHeight) {
        if (!visible || flashTicksLeft > 0) {
            return false;
        }

        var left = sprite.getX() + 5;
        var right = sprite.getX() + SPRITE_WIDTH - 5;
        var top = sprite.getY() + 5;
        var bottom = sprite.getY() + SPRITE_HEIGHT - 5;

        return otherX + otherWidth > left
                && otherX < right
                && otherY + otherHeight > top
                && otherY < bottom;
    }

    public void collect() {
        visible = false;
        flashTicksLeft = FLASH_DURATION_TICKS;
        waitTicks = APPEAR_DELAY_TICKS;
    }
}