package pack.NULLstorm.main;

import textures.Textures;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy2 extends IEnemy{

	private int x;
	private int y;
	private double speed;
	private int health;
	// private int shootTimer;
	private double multiplierAmount;
	private int cashAmount;
	
	public Enemy2(int x, int y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		score = 15;
		health = 100 * MainGamePanel.wh.healthscale;
		shootTimer = (int)(50 + Math.random() * 100);
		multiplierAmount = .1;
		cashAmount = 45;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(Textures.enemy2, this.getX() - 
				(Textures.enemy2.getWidth() / 2), this.getY() - 
				(Textures.enemy2.getHeight() / 2), null);
	}
	public void update() 
	{
		
		//shoot things stuff
		if (x > NULLstormActivity.getWidthVar()*.75){
			if (y <= MainGamePanel.player.getY()){
				y++;
			}
			
			else if (y > MainGamePanel.player.getY()){
				y--;
			}
		}
		
		if (x <= NULLstormActivity.getWidthVar()*.75){
			speed = -10;
			/*shootTimer -= 1;
			if (shootTimer <= 0)
			{
				//MainGamePanel.eBulletHandler.add(0, 0, -4, getX() + 3, y);
				MainGamePanel.eBulletHandler.add(0, 0.3, -4, getX() + 3, y);
				MainGamePanel.eBulletHandler.add(0, -0.3, -4, getX() + 3, y);
				shootTimer = (int)(50 + Math.random() * 100);
			}*/
		}
		
		//else if (x <= 1){
			//if enemy passes left side reset to right side
			//speed -= 1;
			if (x <= 1) {
				x = (int) ((NULLstormActivity.getWidthVar() + 30) + (Math.random() * 500));
				y = (int) (30 + (Math.random() * ((NULLstormActivity.getHeightVar() 
						-Textures.enemy2.getHeight()) - 30)));
				speed = -(Math.abs(Math.random() * 2));
			}
		//}
		// move here
		x += speed;
	}
	
	public Rect getBounds(){
		return new Rect((int)getX() - Textures.enemy2.getWidth()/6,
				(int)getY() - Textures.enemy2.getHeight()/2,
				(int)getX() + Textures.enemy2.getWidth()/2,
				(int)getY() + Textures.enemy2.getHeight()/2
				);
	}
	
	public boolean isDead(){
		if (health <= 0) return true;
		return false;
	}
	
	public void hurt(int damage){
		health -= damage;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
	public void setX(int value) {
		x = value;
	}
	

	public void setY(int value) {
		y = value;
	}
	
	public double getMultiplier(){return multiplierAmount;}
	public int getCash(){return cashAmount;}
}
