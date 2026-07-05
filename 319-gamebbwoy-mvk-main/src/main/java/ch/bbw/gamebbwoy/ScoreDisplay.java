package ch.bbw.gamebbwoy;

import ch.bbw.gamebbwoy.api.PixelColor;
import ch.bbw.gamebbwoy.api.PixelDisplay;

public class ScoreDisplay {

    private static final int DIGIT_WIDTH = 4;
    private static final int DIGIT_HEIGHT = 5;
    private static final int DIGIT_SPACING = 1;

    private static final int[][][] DIGITS = {
            {{1,1,1},{1,0,1},{1,0,1},{1,0,1},{1,1,1}}, // 0
            {{0,1,0},{1,1,0},{0,1,0},{0,1,0},{1,1,1}}, // 1
            {{1,1,1},{0,0,1},{0,1,0},{1,0,0},{1,1,1}}, // 2
            {{1,1,1},{0,0,1},{0,1,1},{0,0,1},{1,1,1}}, // 3
            {{1,0,1},{1,0,1},{1,1,1},{0,0,1},{0,0,1}}, // 4
            {{1,1,1},{1,0,0},{1,1,1},{0,0,1},{1,1,1}}, // 5
            {{1,1,1},{1,0,0},{1,1,1},{1,0,1},{1,1,1}}, // 6
            {{1,1,1},{0,0,1},{0,1,0},{0,1,0},{0,1,0}}, // 7
            {{1,1,1},{1,0,1},{1,1,1},{1,0,1},{1,1,1}}, // 8
            {{1,1,1},{1,0,1},{1,1,1},{0,0,1},{1,1,1}}, // 9
    };

    private static final int[][] LETTER_S = {
            {1,1,1},
            {1,0,0},
            {1,1,1},
            {0,0,1},
            {1,1,1}
    };

    private static final int[][] LETTER_C = {
            {1,1,1},
            {1,0,0},
            {1,0,0},
            {1,0,0},
            {1,1,1}
    };

    private static final int[][] LETTER_O = {
            {1,1,1},
            {1,0,1},
            {1,0,1},
            {1,0,1},
            {1,1,1}
    };

    private static final int[][] LETTER_R = {
            {1,1,0},
            {1,0,1},
            {1,1,0},
            {1,0,1},
            {1,0,1}
    };

    private static final int[][] LETTER_E = {
            {1,1,1},
            {1,0,0},
            {1,1,1},
            {1,0,0},
            {1,1,1}
    };

    private static final int[][][] LETTERS = {LETTER_S, LETTER_C, LETTER_O, LETTER_R, LETTER_E};

    private final int startX;
    private final int startY;

    public ScoreDisplay(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void draw(PixelDisplay graphic, int score) {
        var offsetX = 0;

        for (var letter : LETTERS) {
            drawBitmap(graphic, letter, startX + offsetX, startY);
            offsetX += DIGIT_WIDTH + DIGIT_SPACING;
        }

        graphic.setPixel(startX + offsetX, startY + 1, PixelColor.BLACK);
        graphic.setPixel(startX + offsetX, startY + 3, PixelColor.BLACK);
        offsetX += DIGIT_SPACING + 2;

        for (var ch : String.valueOf(score).toCharArray()) {
            drawBitmap(graphic, DIGITS[ch - '0'], startX + offsetX, startY);
            offsetX += DIGIT_WIDTH + DIGIT_SPACING;
        }
    }

    private void drawBitmap(PixelDisplay graphic, int[][] bitmap, int x, int y) {
        for (var row = 0; row < bitmap.length; row++) {
            for (var col = 0; col < bitmap[row].length; col++) {
                if (bitmap[row][col] == 1) {
                    graphic.setPixel(x + col, y + row, PixelColor.BLACK);
                }
            }
        }
    }
}