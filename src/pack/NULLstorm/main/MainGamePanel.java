package pack.NULLstorm.main;

import textures.Textures;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import audio.AudioHandler;

public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback {
	// private SensorManager mSensorManager;
	// private Sensor mSensor;
	static boolean pause;
	// static boolean gameOver;
	MainThread thread;
	Textures texture;
	static AudioHandler audio;
	static WaveHandler wh;
	//static StarHandler bg;
	static Background bg;
	static Player player;
	static EnemyHandler enemyHandler;
	static BulletHandler bulletHandler;
	static EnemyBulletHandler eBulletHandler;
	static EffectHandler effects;
	static Item item;
	static Menu menu;
	static GUI gui;
	static Shop shop;
	static int gameState = 0; //shop test
	int press = 0;
	static boolean isPaused;
	Paint paint = new Paint();
	static Context multiClassContext;

	public MainGamePanel(Context context) {
		super(context);
		multiClassContext = context;
		getHolder().addCallback(this);
		// initiate objects
		texture = new Textures(context);
		//bg = new StarHandler();
		bg = new Background();
		wh = new WaveHandler();
		player = new Player(NULLstormActivity.getWidthVar() / 2,
				(int) (NULLstormActivity.getHeightVar() / 1.5));
		audio = new AudioHandler(context);
		enemyHandler = new EnemyHandler();
		bulletHandler = new BulletHandler();
		eBulletHandler = new EnemyBulletHandler();
		effects = new EffectHandler();
		item = new Item();
		menu = new Menu();
		gui = new GUI();
		shop = new Shop();
		isPaused = false;

		// start game
		thread = new MainThread(getHolder(), this);
		setFocusable(true);
	}

	protected void paint(Canvas canvas) {
		// things will be drawn here
		canvas.drawColor(Color.BLACK);

		bg.draw(canvas);
		switch (gameState) {
		case (0): //menu
			menu.draw(canvas);
			effects.draw(canvas);
			break;
		case (1): //gameplay
			player.draw(canvas);
			enemyHandler.draw(canvas);
			bulletHandler.draw(canvas);
			eBulletHandler.draw(canvas);
			item.draw(canvas);
			gui.draw(canvas);
			effects.draw(canvas);
			break;
		case (2): //gameover
			paint.setAntiAlias(true);
			paint.setTextSize(12);
			paint.setARGB(255, 255, 255, 255);
			canvas.drawBitmap(
					Textures.gameOver,
					(NULLstormActivity.getWidthVar() / 2)
							- (Textures.gameOver.getWidth() / 2),
					(NULLstormActivity.getHeightVar() / 2)
							- (Textures.gameOver.getHeight()), null);
			canvas.drawText("Score: " + player.getScore(),
					(NULLstormActivity.getWidthVar() / 2) - 90,
					(NULLstormActivity.getHeightVar() / 2) + 12, paint);
			break;
		case (3):
			shop.draw(canvas);
			break;
		}
	}
	public void update() {
		if (!isPaused) {
			bg.update();
			switch (gameState) {
			case (0): //menu
				bg.setSpeed(2, 2);
				menu.update();
				break;

			case (1): //gameplay
				player.update();
				if (press == 1)
					player.ShootUpdate();
				enemyHandler.update();
				bulletHandler.update();
				eBulletHandler.update();
				item.update();
				wh.update();

				break;
			case (2): //gameover
				break;
			case (3):
				break;
			}
		}
	}

	public void reset() {
		wh.reset();
		player.reset();
		
		enemyHandler.reset();
		bulletHandler.removeAll();
		eBulletHandler.removeAll();
		item.setY(600);
		gui.setMessage("Game Restarted");
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	/*
	 * public void onSensorChanged(SensorEvent e) { // placeholder }
	 */

	public boolean onTouchEvent(MotionEvent event) {
		switch (gameState) {
		case (0): // menu
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				menu.select((int) event.getX(), (int) event.getY());
			} else if (event.getAction() == MotionEvent.ACTION_MOVE)
				MainGamePanel.effects.addExplosion((int) event.getX()
						+ (int) (Math.random() * 60) - 30, (int) event.getY()
						+ (int) (Math.random() * 60) - 30,
						32 + (int) (Math.random() * 64), 0);
			break;

		case (1): //gameplay
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				menu.reset();
				
				if (event.getY() < NULLstormActivity.getHeightVar() / 8
						&& event.getX() > NULLstormActivity.getWidthVar()
								- Textures.getTexture("pause").getWidth() - 15) {
					if (isPaused == false)
						isPaused = true;
					else
						isPaused = false;
				} else if (event.getY() < NULLstormActivity.getHeightVar() / 8
						&& event.getX() > NULLstormActivity.getWidthVar() - (Textures.getTexture("menubutton").getWidth() + (Textures.getTexture("pause").getWidth()) + 30))
				{
				
					gameState = 0;
				}
				if (press == 0){
					press = 1;
					player.initFire();
				} else
					press = 0;
			}
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				// press = 1;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
			}
			break;
		case (2): //gameover
			if (event.getAction() == MotionEvent.ACTION_UP) {
				press = 0;
				menu.reset();
				
				if ((event.getY() > (NULLstormActivity.getHeightVar() / 2)
						- (Textures.gameOver.getHeight() + 45) && event.getY() < NULLstormActivity
						.getHeightVar() / 2)) {
					if (event.getX() < NULLstormActivity.getWidthVar() / 2) {
						
						gameState = 1;
						reset();
					} else {
						
						gameState = 0;
						reset();
					}
				}
			}
			break;
		case (3):
			if (event.getAction() == MotionEvent.ACTION_DOWN)
			shop.select((int)event.getX(), (int)event.getY());
			break;
		}
		return true;
	}

	public void setPause(boolean newPause) {
		gameState = 0;
		pause = newPause;
	}
	public boolean isPaused() {
		return pause;
	}
	public void setState(int i) {
		gameState = i;
	}
}
