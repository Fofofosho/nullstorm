package pack.NULLstorm.main;
import textures.Textures;
import android.graphics.Canvas;

public class Menu {
	Button title, start, settings, credits, calib, back;
	int width, height, fade;
	boolean fadeOut;
	int menuState;
	double bigPlaysX;
	
	public Menu(){
		MainGamePanel.audio.playMusic(R.raw.auroraaudiomenu);
		fade = 255;
		title = new Button(Textures.title, 0, 0,
				(Textures.title.getWidth()) * 3,
				(Textures.title.getHeight()) * 3, 1, 1);
		start = new Button(Textures.start, 0, 0,
				(Textures.start.getWidth()) * 2,
				(Textures.start.getHeight()) * 2, 1, 1);
		settings = new Button(Textures.settings, 0, 0,
				(Textures.settings.getWidth()) * 2,
				(Textures.settings.getHeight()) * 2, 1, 1);
		credits = new Button(Textures.credits, 0, 0,
				(Textures.credits.getWidth()) * 2,
				(Textures.credits.getHeight()) * 2, 1, 1);
		calib = new Button(Textures.calibrate, 0, 0,
				(Textures.calibrate.getWidth()) * 2,
				(Textures.calibrate.getHeight()) * 2, 1, 1);
		back = new Button(Textures.back, 0, 0,
				(Textures.back.getWidth()) * 2,
				(Textures.back.getHeight()) * 2, 1, 1);
		MainGamePanel.player.setX(NULLstormActivity.getWidthVar() / 2);
		MainGamePanel.player.setY(NULLstormActivity.getHeightVar() / 2);
	}
	public void draw(Canvas canvas){
		if (width != canvas.getWidth() || height != canvas.getHeight()){
			//set up width and button placement
			width = canvas.getWidth();
			height = canvas.getHeight();
			//update button placement
			title.update(width / 2, 96);
			settings.update(width / 6, height - 64);
			start.update(width / 2, height - 64);
			credits.update((width / 6) * 5, height - 64);
			calib.update((width / 6) * 5, height - 64);
			back.update(width / 6, height - 64);
		} else {
			switch (menuState) {
			case 0: // main menu
				// update fade effect
				
				if (fadeOut) {
					// start game by fading into it
					if (fade - 5 > 0) {
						fade -= 5;
					} else {
						MainGamePanel.gameState = 1;
						MainGamePanel.player.setPainTime(100);
						MainGamePanel.audio.stopAll();
					}
				}
			
				// draw buttons
				title.draw(canvas, fade);
				start.draw(canvas, fade);
				settings.draw(canvas, fade);
				credits.draw(canvas, fade);
				break;
			case 1: // settings


				
				MainGamePanel.player.draw(canvas);
				back.draw(canvas, fade);
				calib.draw(canvas, fade);
				break;
			case 2: // credits

				canvas.drawBitmap(
						Textures.dacredits,
						(NULLstormActivity.getWidthVar() / 2)
								- (Textures.dacredits.getWidth() / 2),
						((NULLstormActivity.getHeightVar() / 2)- (Textures.dacredits.getHeight()/2) - (Textures.dacredits.getHeight()/3 ) + 50), null);

				back.draw(canvas, fade);
				break;
			}
		}
	}

	public void update() {
		switch (menuState) {
		case 0: // main menu
			break;
		case 1: // settings
			bigPlaysX = NULLstormActivity.getAccelX();
			MainGamePanel.player.update();
			break;
		case 2: // credits
			break;
		}
	}
	public void select(int x, int y) {
		switch (menuState) {

		case 0: // main menu
			MainGamePanel.effects.addExplosion(x, y, 96, 0);
			if (start.select(x, y)) { // start button
				start.hide();
				fadeOut();
			}
			if (settings.select(x, y)) {
				settings.hide();
				menuState = 1;
				calib.reveal();
				back.reveal();
			}
			if (credits.select(x, y)) {
				credits.hide();
				menuState = 2;
				back.reveal();
			}
			break;
		case 1: // settings
			if (calib.select(x, y)) {
				MainGamePanel.player.setCalb(bigPlaysX);
				MainGamePanel.player.setX(NULLstormActivity.getWidthVar() / 2);
				MainGamePanel.player.setY(NULLstormActivity.getHeightVar() / 2);
				NULLstormActivity.setCalib((float)bigPlaysX);
			}
			if (back.select(x, y)) {
				back.hide();
				menuState = 0;
				start.reveal();
				settings.reveal();
				credits.reveal();
				settings.reveal();
				credits.reveal();


			}
			// menuState = 0;
			break;
		case 2: // credits
			if (back.select(x, y)) {
				back.hide();
				menuState = 0;
				start.reveal();
				settings.reveal();
				credits.reveal();
				settings.reveal();
				credits.reveal();

			}
			break;

		}
	}
	public void fadeOut(){
		fade = 255;
		fadeOut = true;
	}
	public void reset(){
		fade=255;
		fadeOut=false;
		start.reveal();
		settings.reveal();
		credits.reveal();
		settings.reveal();
		credits.reveal();
	}
}
