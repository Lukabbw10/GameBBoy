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
	private Mario mario;
	private List<Obstacle> obstacles = new ArrayList<>();
	private int score = 0;
	private GameState state = GameState.INTRO;
	private PixelDisplay graphic;
	private boolean speedIncreased = false;

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
		drawM(graphic, 60, 50);
		drawPressSpace(graphic);
	}

	private void drawGameOver(PixelDisplay graphic) {
		for (var x = 0; x < graphic.getPixelWidth(); x++) {
			for (var y = 0; y < graphic.getPixelHeight(); y++) {
				graphic.setPixel(x, y, PixelColor.BLACK);
			}
		}
		drawPressSpace(graphic);
	}

	private void drawM(PixelDisplay graphic, int startX, int startY) {
		var m = new int[][]{
				{1, 0, 0, 0, 1},
				{1, 1, 0, 1, 1},
				{1, 0, 1, 0, 1},
				{1, 0, 0, 0, 1},
				{1, 0, 0, 0, 1}
		};

		for (var row = 0; row < m.length; row++) {
			for (var col = 0; col < m[row].length; col++) {
				if (m[row][col] == 1) {
					graphic.setPixel(startX + col * 3, startY + row * 3, PixelColor.BLACK);
					graphic.setPixel(startX + col * 3 + 1, startY + row * 3, PixelColor.BLACK);
					graphic.setPixel(startX + col * 3, startY + row * 3 + 1, PixelColor.BLACK);
					graphic.setPixel(startX + col * 3 + 1, startY + row * 3 + 1, PixelColor.BLACK);
				}
			}
		}
	}

	private void drawPressSpace(PixelDisplay graphic) {
		for (var x = 40; x < 120; x++) {
			graphic.setPixel(x, 120, PixelColor.BLACK);
			graphic.setPixel(x, 122, PixelColor.BLACK);
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