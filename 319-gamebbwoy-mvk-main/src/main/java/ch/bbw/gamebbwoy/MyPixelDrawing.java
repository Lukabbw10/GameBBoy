package ch.bbw.gamebbwoy;

import ch.bbw.gamebbwoy.api.ButtonListener;
import ch.bbw.gamebbwoy.api.PixelColor;
import ch.bbw.gamebbwoy.api.PixelDisplay;
import ch.bbw.gamebbwoy.api.PixelDrawing;
import ch.bbw.gamebbwoy.internal.GameBbwoy;

import java.util.ArrayList;
import java.util.List;

public class MyPixelDrawing implements PixelDrawing, ButtonListener {
	private static final int MARIO_WIDTH = 20;
	private static final int MARIO_HEIGHT = 20;
	private static final int MARIO_X = 10;
	private static final int LETTER_WIDTH = 3;
	private static final int LETTER_HEIGHT = 4;
	private static final int LETTER_SPACING = 1;

	private static final int[][] LETTER_M = {
			{1, 0, 0, 0, 1},
			{1, 1, 0, 1, 1},
			{1, 0, 1, 0, 1},
			{1, 0, 0, 0, 1},
			{1, 0, 0, 0, 1}
	};
	private static final int[][] LETTER_A = {
			{0, 1, 1, 0},
			{1, 0, 0, 1},
			{1, 1, 1, 1},
			{1, 0, 0, 1},
			{1, 0, 0, 1}
	};
	private static final int[][] LETTER_R = {
			{1, 1, 0},
			{1, 0, 1},
			{1, 1, 0},
			{1, 0, 1},
			{1, 0, 1}
	};
	private static final int[][] LETTER_I = {
			{1, 1, 1},
			{0, 1, 0},
			{0, 1, 0},
			{0, 1, 0},
			{1, 1, 1}
	};
	private static final int[][] LETTER_O = {
			{1, 1, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 1, 1}
	};
	private static final int[][] LETTER_U = {
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 0, 1},
			{1, 1, 1}
	};
	private static final int[][] LETTER_N = {
			{1, 0, 0, 1},
			{1, 1, 0, 1},
			{1, 0, 1, 1},
			{1, 0, 0, 1},
			{1, 0, 0, 1}
	};
	private static final int[][] LETTER_E = {
			{1, 1, 1},
			{1, 0, 0},
			{1, 1, 1},
			{1, 0, 0},
			{1, 1, 1}
	};
	private static final int[][] LETTER_S = {
			{1, 1, 1},
			{1, 0, 0},
			{1, 1, 1},
			{0, 0, 1},
			{1, 1, 1}
	};
	private static final int[][] LETTER_T = {
			{1, 1, 1},
			{0, 1, 0},
			{0, 1, 0},
			{0, 1, 0},
			{0, 1, 0}
	};
	private static final int[][] LETTER_P = {
			{1, 1, 1},
			{1, 0, 1},
			{1, 1, 1},
			{1, 0, 0},
			{1, 0, 0}
	};
	private static final int[][] LETTER_C = {
			{1, 1, 1},
			{1, 0, 0},
			{1, 0, 0},
			{1, 0, 0},
			{1, 1, 1}
	};
	private static final int[][] SPACE = {
			{0, 0},
			{0, 0},
			{0, 0},
			{0, 0},
			{0, 0}
	};

	private Mario mario;
	private List<Obstacle> obstacles = new ArrayList<>();
	private int score = 0;
	private GameState state = GameState.INTRO;
	private PixelDisplay graphic;
	private boolean speedIncreased = false;
	private final ScoreDisplay scoreDisplay = new ScoreDisplay(5, 5);

	public static void main(String[] args) throws Throwable {
		GameBbwoy.playGame(new MyPixelDrawing());
	}

	@Override
	public void initialize(PixelDisplay graphic) {
		this.graphic = graphic;
		mario = new Mario(graphic);
		obstacles.add(new Obstacle(graphic, 0));
	}

	@Override
	public void tick(PixelDisplay graphic) {
		graphic.clear();

		switch (state) {
			case INTRO -> drawIntro(graphic);
			case PLAYING -> playGame(graphic);
			case GAME_OVER -> drawGameOver(graphic);
		}
	}

	private void playGame(PixelDisplay graphic) {
		mario.tick(graphic);
		scoreDisplay.draw(graphic, score);

		if (score >= 2 && obstacles.size() < 2) {
			obstacles.add(new Obstacle(graphic, 40));
		}

		if (score >= 4 && !speedIncreased) {
			Obstacle.increaseSpeed();
			speedIncreased = true;
		}

		for (var obs : new ArrayList<>(obstacles)) {
			obs.tick(graphic);
			if (obs.isOffScreen()) {
				obstacles.remove(obs);
				obstacles.add(new Obstacle(graphic, 0));
				score++;
			}
			if (isColliding(obs)) {
				state = GameState.GAME_OVER;
			}
		}
	}

	private void drawIntro(PixelDisplay graphic) {
		var title = new int[][][]{LETTER_M, LETTER_A, LETTER_R, LETTER_I, LETTER_O, SPACE, LETTER_R, LETTER_U, LETTER_N};
		drawText(graphic, title, 25, 25, 3);

		var startText = new int[][][]{LETTER_S, LETTER_T, LETTER_A, LETTER_R, LETTER_T};
		drawText(graphic, startText, 55, 80, 2);

		var pressText = new int[][][]{LETTER_P, LETTER_R, LETTER_E, LETTER_S, LETTER_S, SPACE,
				LETTER_S, LETTER_P, LETTER_A, LETTER_C, LETTER_E};
		drawText(graphic, pressText, 35, 110, 2);
	}

	private void drawGameOver(PixelDisplay graphic) {
		for (var x = 0; x < graphic.getPixelWidth(); x++) {
			for (var y = 0; y < graphic.getPixelHeight(); y++) {
				graphic.setPixel(x, y, PixelColor.WHITE);
			}
		}

		var gameOverText = new int[][][]{LETTER_R, LETTER_E, LETTER_S, LETTER_T, LETTER_A, LETTER_R, LETTER_T};
		drawText(graphic, gameOverText, 50, 60, 2);

		var pressText = new int[][][]{LETTER_P, LETTER_R, LETTER_E, LETTER_S, LETTER_S, SPACE,
				LETTER_S, LETTER_P, LETTER_A, LETTER_C, LETTER_E};
		drawText(graphic, pressText, 35, 90, 2);
	}

	private void drawText(PixelDisplay graphic, int[][][] letters, int startX, int startY, int scale) {
		var offsetX = startX;

		for (var letter : letters) {
			drawLetter(graphic, letter, offsetX, startY, scale);
			var letterWidth = letter[0].length;
			offsetX += (letterWidth + 1) * scale;
		}
	}

	private void drawLetter(PixelDisplay graphic, int[][] letter, int startX, int startY, int scale) {
		for (var row = 0; row < letter.length; row++) {
			for (var col = 0; col < letter[row].length; col++) {
				if (letter[row][col] == 1) {
					for (var sx = 0; sx < scale; sx++) {
						for (var sy = 0; sy < scale; sy++) {
							graphic.setPixel(startX + col * scale + sx, startY + row * scale + sy, PixelColor.BLACK);
						}
					}
				}
			}
		}
	}

	private boolean isColliding(Obstacle obs) {
		var marioLeft = (double) MARIO_X + 3;
		var marioRight = (double) MARIO_X + MARIO_WIDTH - 5;
		var marioTop = mario.getY() + 3;
		var marioBottom = mario.getY() + MARIO_HEIGHT - 3;
		var obstacleLeft = obs.getX() + 4;
		var obstacleRight = obs.getX() + 21;
		var obstacleTop = obs.getY() + 8;
		var obstacleBottom = obs.getY() + 25;

		return marioRight > obstacleLeft
				&& marioLeft < obstacleRight
				&& marioBottom > obstacleTop
				&& marioTop < obstacleBottom;
	}

	private void restartGame() {
		score = 0;
		speedIncreased = false;
		Obstacle.resetSpeed();
		state = GameState.PLAYING;
		mario = new Mario(graphic);
		obstacles.clear();
		obstacles.add(new Obstacle(graphic, 0));
	}

	@Override
	public void onButtonPress(GameButton button) {
		switch (state) {
			case INTRO -> {
				if (button == GameButton.SPACE) {
					state = GameState.PLAYING;
				}
			}
			case PLAYING -> {
				if (button == GameButton.UP) {
					mario.jump();
				}
			}
			case GAME_OVER -> {
				if (button == GameButton.SPACE) {
					restartGame();
				}
			}
		}
	}

	@Override
	public void onButtonRelease(GameButton button) {
	}
}