package pack.NULLstorm.main;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class NULLstormActivity extends Activity implements SensorEventListener {
	static MainGamePanel game;
	static int highScore;

	String result = null;
	InputStream inputStream = null;
	StringBuilder stringBuilder = null;

	// Window Manager screen metrics depending on orientation
	private DisplayMetrics metrics = new DisplayMetrics();
	private static int heightVar;
	private static int widthVar;

	// SensorManger and Sensor variables
	private SensorManager mSensorManager;
	private Sensor mSensor;

	// Accelerometer values
	private static float accelX;
	private static float accelY;
	private float accelZ;

	private String clientKey;
	private static String key;
	private static float calib;
	private SecureRandom random = new SecureRandom();
	public static final String PREFS_NAME = "NULLprefs";

	// stuff

	SharedPreferences settings;
	SharedPreferences.Editor editor;

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

	public SensorManager getSensorManager() {
		return mSensorManager;
	}

	public static String getKey() {
		return key;
	}

	public Sensor getSensor() {
		return mSensor;
	}

	public static int getHeightVar() {
		return heightVar;
	}

	public static int getWidthVar() {
		return widthVar;
	}

	public static float getAccelX() {
		return accelX;
	}

	public static float getAccelY() {
		return accelY;
	}

	public float getAccelZ() {
		return accelZ;
	}

	public void onCreate(Bundle savedInstanceState) {
		// Initial construct
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Screen Metrics initialized
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		widthVar = metrics.widthPixels;
		heightVar = metrics.heightPixels;

		// Accelerometer initialized
		SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor mSensor = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		// Register sensor iff registerListener returns true, otherwise return
		// error
		if (!mSensorManager.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_GAME)) {
			// Deal with the error!
		}

		// Initialize the game panel
		game = new MainGamePanel(this);

		// Put in container
		setContentView(game);

		// SharedPreferences
		settings = getSharedPreferences(PREFS_NAME, 0);
		clientKey = settings.getString("clientKeyPref", "undefined");
		if (clientKey.equals("undefined")) {
			clientKey = null;
			clientKey = nextSessionId();
			// SharedPreferences.Editor
			editor = settings.edit();
			editor.putString("clientKeyPref", clientKey);
			editor.commit();
			key = clientKey;
		}
		//Log.v("Test", Float.toString(calib));
		//calib = settings.getFloat("calibration", (float)-10.0);
		//Log.v("TestA", Float.toString(calib));
		if (calib == -10)
		{
			//Log.v("Test1", Float.toString(calib));
			editor = settings.edit();
			editor.putFloat("calibration", calib);
			editor.commit();
			//Log.v("Test2", Float.toString(calib));
		}
		//Log.v("Test", Float.toString(calib));

		MainGamePanel.player.setCalb(calib);

		key = clientKey;
	}

	public static void setCalib(float value) {
		calib = value;
	}
	
	public static float getCalib(){
		return calib;
	}

	//public void storeCalib() {
	//	settings = getSharedPreferences(PREFS_NAME, 0);
	//	editor = settings.edit();
	//	editor.putFloat("Calibration", calib);
	//}

	protected void onPause() {
		// Set high score
		MainGamePanel.gameState = 0;
		MainGamePanel.menu.reset();
		MainGamePanel.audio.stopAll();
		SharedPreferences settings = this.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putInt("highScore", MainGamePanel.player.getHighScore());
		highScore = settings.getInt("highScore", 0);
		editor.commit();

		// Pause game
		game.thread.setRunning(false);
		finish();
		super.onPause();
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onStop() {
		finish();
		super.onStop();
	}

	protected void onDestroy() {
		finish();
		super.onDestroy();
		MainGamePanel.player.reset();
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	public void onSensorChanged(SensorEvent event) {
		accelX = event.values[0];
		accelY = event.values[1];
		accelZ = event.values[2];
	}
}