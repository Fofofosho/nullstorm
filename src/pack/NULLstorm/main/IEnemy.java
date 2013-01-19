package pack.NULLstorm.main;

import android.graphics.Canvas;
import android.graphics.Rect;
//import android.graphics.Color;
//import android.graphics.Paint;

//all enemies should have these methods
public abstract class IEnemy {
	public int xPos = 0;
	public int yPos = 0;
	public double speed = 0.0;
	public int health = 0;
	public static int score = 0;
	public int shootTimer;
	public int type;
	public double multiplierAmount;
	public int cashAmount;
	
	public void draw(Canvas canvas){}
	public void update(){}
	public int getX(){return xPos;}
	public int getY(){return yPos;}
	public void setX(int value){}
	public void setY(int value){}
	public int getScore() {return score;}
	public boolean isDead() {return true;}
	public void hurt(int damage){}
	public Rect getBounds() {return null;}
	public double getMultiplier(){return multiplierAmount;}
	public int getCash(){return cashAmount;}

}
