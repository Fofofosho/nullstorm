package pack.NULLstorm.main;

import textures.Textures;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Background {
	StarHandler stars;
	//coordinates
	int width, height;
	double x1, x2, y1, y2;
	
	public Background(){
		stars = new StarHandler();
	}
	public void draw(Canvas canvas){
		Paint paint = new Paint();
		paint.setAlpha(150);
		if (width != canvas.getWidth() || height != canvas.getHeight()){
			//set screen width and height for all devices
			width = canvas.getWidth();
			height = canvas.getHeight();
			//set up the spawn one time only!
			x1 = (int)(Math.random()*(width*3));
			y1 = (int)(Math.random()*(height));
			x2 = (int)(Math.random()*(width*3));
			y2 = (int)(Math.random()*(height));
		}
		stars.draw(canvas);
		//draw space clouds
		//WARNING - DO NOT USE 128, USE A PROPER CENTERING TECHNIQUE
		canvas.drawBitmap(Textures.getTexture("spacecloud1"),(int)(x1-128),(int)(y1-128),paint);
		canvas.drawBitmap(Textures.getTexture("spacecloud2"),(int)(x2-128),(int)(y2-128),paint);
	}
	public void update(){
		stars.update();
		//check space clouds
		if (x1 > -512) x1-=2;
		else{
			//respawn
			x1 = (int)(Math.random()*512)+width;
			y1 = (int)(Math.random()*(height));
		}
		if (x2 > -512) x2-=2;
		else{
			//respawn
			x2 = (int)(Math.random()*512)+width;
			y2 = (int)(Math.random()*(height));
		}
	}
	public void setSpeed(double range, double factor){
		stars.setSpeed(range, factor);
	}
}
