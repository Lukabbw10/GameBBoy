package ch.bbw.gamebbwoy;

import ch.bbw.gamebbwoy.api.ButtonListener;
import ch.bbw.gamebbwoy.api.PixelDisplay;
import ch.bbw.gamebbwoy.api.PixelDrawing;
import ch.bbw.gamebbwoy.internal.GameBbwoy;

public class MyPixelDrawing implements PixelDrawing, ButtonListener {

	private Mario mario;

	public static void main(String[] args) throws Throwable {
		GameBbwoy.playGame(new MyPixelDrawing());
	}

	@Override
	public void initialize(PixelDisplay graphic) {
		mario = new Mario(graphic);
	}

	@Override
	public void tick(PixelDisplay graphic) {
		graphic.clear();
		mario.tick(graphic);
	}

	@Override
	public void onButtonPress(GameButton button) {
		if (button == GameButton.UP) {
			mario.jump();
		}
	}

	@Override
	public void onButtonRelease(GameButton button) {
		// nicht benötigt
	}
}