package textures;

import pack.NULLstorm.main.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;

public class Textures extends View
{
	public static Bitmap player;
	public static Bitmap enemy;
	public static Bitmap enemy2;
	public static Bitmap enemy3;
	public static Bitmap boss1;
	public static Bitmap star;
	public static Bitmap spacecloud1;
	public static Bitmap spacecloud2;
	public static Bitmap item;
	public static Bitmap bullet;
	public static Bitmap bullet2;
	public static Bitmap explosion;
	public static Bitmap gui_quit;
	public static Bitmap ammo1;
	public static Bitmap ammo2;
	public static Bitmap gameOver;
	public static Bitmap pause;
	public static Bitmap resume;
	public static Bitmap credits;
	public static Bitmap dacredits;
	public static Bitmap settings;
	public static Bitmap start;
	public static Bitmap title;
	public static Bitmap shopexit;
	public static Bitmap shopbuy;
	public static Bitmap shopbulletpower;
	public static Bitmap shopbulletspread;
	public static Bitmap shopbulletrate;
	public static Bitmap shopadd;
	public static Bitmap shopminus;
	public static Bitmap shoplives;
	public static Bitmap menubutton;
	public static Bitmap calibrate;
	public static Bitmap back;

	public Textures(Context context)
	{
		super(context);
		// objects
		player = BitmapFactory.decodeResource(getResources(), R.drawable.player);
		enemy = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
		enemy2 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2);
		enemy3 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy3);
		boss1 = BitmapFactory.decodeResource(getResources(), R.drawable.boss1);
		star = BitmapFactory.decodeResource(getResources(), R.drawable.star);
		spacecloud1 = BitmapFactory.decodeResource(getResources(), R.drawable.spacecloud1);
		spacecloud2 = BitmapFactory.decodeResource(getResources(), R.drawable.spacecloud2);
		item = BitmapFactory.decodeResource(getResources(), R.drawable.item);
		bullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
		bullet2 = BitmapFactory.decodeResource(getResources(), R.drawable.bullet2);
		explosion = BitmapFactory.decodeResource(getResources(), R.drawable.explosion);
		//gui
		pause = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
		resume = BitmapFactory.decodeResource(getResources(), R.drawable.resume);
		gui_quit = BitmapFactory.decodeResource(getResources(), R.drawable.gui_quit);
		ammo1 = BitmapFactory.decodeResource(getResources(), R.drawable.ammo1);
		ammo2 = BitmapFactory.decodeResource(getResources(), R.drawable.ammo2);
		gameOver = BitmapFactory.decodeResource(getResources(), R.drawable.game_over);
		//menu
		credits = BitmapFactory.decodeResource(getResources(), R.drawable.credits);
		dacredits = BitmapFactory.decodeResource(getResources(), R.drawable.dacredits);
		settings = BitmapFactory.decodeResource(getResources(), R.drawable.settings);
		start = BitmapFactory.decodeResource(getResources(), R.drawable.start);
		title = BitmapFactory.decodeResource(getResources(), R.drawable.title);
		//shop
		shopexit = BitmapFactory.decodeResource(getResources(), R.drawable.shopexit);
		shopbuy = BitmapFactory.decodeResource(getResources(), R.drawable.shopbuy);
		shopbulletpower = BitmapFactory.decodeResource(getResources(), R.drawable.shopbulletpower);
		shopbulletspread = BitmapFactory.decodeResource(getResources(), R.drawable.shopbulletspread);
		shopbulletrate = BitmapFactory.decodeResource(getResources(), R.drawable.shopbulletrate);
		shopadd = BitmapFactory.decodeResource(getResources(), R.drawable.shopadd);
		shopminus = BitmapFactory.decodeResource(getResources(), R.drawable.shopminus);
		shoplives = BitmapFactory.decodeResource(getResources(), R.drawable.shoplives);
		menubutton = BitmapFactory.decodeResource(getResources(), R.drawable.menubutton);
		calibrate = BitmapFactory.decodeResource(getResources(), R.drawable.calibrate);
		back = BitmapFactory.decodeResource(getResources(), R.drawable.back);
	}
	public static Bitmap getTexture(String st)
	{
		st = st.toLowerCase();
		if (st.equals("player")){return player;}
		else if (st.equals("enemy")){return enemy;}
		else if (st.equals("enemy2")){return enemy2;}
		else if (st.equals("enemy3")){return enemy3;}
		else if (st.equals("boss1")){return boss1;}
		else if (st.equals("star")){return star;}
		else if (st.equals("spacecloud1")){return spacecloud1;}
		else if (st.equals("spacecloud2")){return spacecloud2;}
		else if (st.equals("item")){return item;}
		else if (st.equals("gui_quit")){return gui_quit;}
		else if (st.equals("ammo1")){return ammo1;}
		else if (st.equals("ammo2")){return ammo2;}
		else if (st.equals("pause")){return pause;}
		else if (st.equals("resume")){return resume;}
		else if (st.equals("credits")){return credits;}
		else if (st.equals("dacredits")){return dacredits;}
		else if (st.equals("settings")){return settings;}
		else if (st.equals("start")){return start;}
		else if (st.equals("title")){return title;}
		else if (st.equals("shopexit")){return shopexit;}
		else if (st.equals("shopbuy")){return shopbuy;}
		else if (st.equals("shopbulletpower")){return shopbulletpower;}
		else if (st.equals("shopbulletspread")){return shopbulletspread;}
		else if (st.equals("shopbulletrate")){return shopbulletrate;}
		else if (st.equals("shopaddplus")){return shopadd;}
		else if (st.equals("shopminusplus")){return shopminus;}
		else if (st.equals("shoplives")){return shoplives;}
		else if (st.equals("menubutton")){return menubutton;}
		else if (st.equals("calibrate")){return calibrate;}
		else if (st.equals("back")){return back;}
		else{return gameOver;}
	}

	public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
	{
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}
}
