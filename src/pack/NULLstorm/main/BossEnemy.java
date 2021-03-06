package pack.NULLstorm.main;

import textures.SpriteHandler;
import textures.Textures;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class BossEnemy extends IEnemy {

	private int x;
	private int y;
	private double speed;
	private int health;
	private int shootTimer;
	private int shootType;
	private SpriteHandler sprite;
	boolean moveup;
	int yspeed;
	private double multiplierAmount;
	private int cashAmount;

	public BossEnemy(int x, int y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		score = 500;
		health = 5000;
		shootTimer = 75;
		shootType = 2;
		type = 1;
		sprite = new SpriteHandler(Textures.boss1.getWidth(), 
				Textures.boss1.getHeight(),4,1,0.25);
		sprite.center();
		moveup = false;
		yspeed = 0;
		multiplierAmount = 1;
		cashAmount = 500;
	}
	public void draw(Canvas canvas) {
		if (sprite.isAnimating()){
			sprite.animate();
			sprite.update(x, y, 128, 128);
		}
		canvas.drawBitmap(Textures.boss1, sprite.spriteRect, sprite.destRect, null);

		Paint paint = new Paint();

		// health bar code
			paint.setColor(Color.RED);
			canvas.drawRect(
					(NULLstormActivity.getWidthVar() / 10)+50,
					NULLstormActivity.getHeightVar()-40,
					(NULLstormActivity.getWidthVar() / 10  + (64 * 5000)/1000)+50, 
					NULLstormActivity.getHeightVar()-24, 
					paint);
			paint.setColor(Color.GREEN);
			canvas.drawRect(
					(NULLstormActivity.getWidthVar() / 10)+50,
					NULLstormActivity.getHeightVar()-40,
					(NULLstormActivity.getWidthVar() / 10  + (64 * health)/1000)+50, 
					NULLstormActivity.getHeightVar()-24, 
					paint);
		

	}

	public void update() {

		// shoot things stuff
		if (x >= 150) {
			shootTimer -= 1;
			if (shootTimer <= 0 && health >= 300) {
				MainGamePanel.audio.playSound(1);
				switch (shootType) {
				case (1):
					//MainGamePanel.eBulletHandler.add(0, 1.5, -3, getX() -50, y - 45);
					MainGamePanel.eBulletHandler.add(0, 1, -3, getX() -50, y -45);
					//MainGamePanel.eBulletHandler.add(0, 0.3, -3, getX() -50, y -45);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() -50, y -45);
					//MainGamePanel.eBulletHandler.add(0, -0.3, -3, getX() -50, y - 45);
					MainGamePanel.eBulletHandler.add(0, -1, -3, getX() -50, y -45);
					//MainGamePanel.eBulletHandler.add(0, -1.5, -3, getX() -50, y - 45);
					
					//MainGamePanel.eBulletHandler.add(0, 1.5, -3, getX() -50, y + 45);
					MainGamePanel.eBulletHandler.add(0, 1, -3, getX() -50, y +45);
					//MainGamePanel.eBulletHandler.add(0, 0.3, -3, getX() -50, y +45);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() -50, y +45);
					//MainGamePanel.eBulletHandler.add(0, -0.3, -3, getX() -50, y + 45);
					MainGamePanel.eBulletHandler.add(0, -1, -3, getX() -50, y +45);
					//MainGamePanel.eBulletHandler.add(0, -1.5, -3, getX() -50, y + 45);
					shootType = 2;
					break;
				case (2):
					MainGamePanel.eBulletHandler.add(0, 1.25, -3, getX() -50, y - 45);
					//MainGamePanel.eBulletHandler.add(0, 0.5, -3, getX() -50, y - 45);
					MainGamePanel.eBulletHandler.add(0, 0.1, -3, getX() -50, y - 45);
					MainGamePanel.eBulletHandler.add(0, -0.1, -3, getX() -50, y - 45);
					//MainGamePanel.eBulletHandler.add(0, -0.5, -3, getX() -50, y - 45);
					MainGamePanel.eBulletHandler.add(0, -1.25, -3, getX() -50,y - 45);
					
					MainGamePanel.eBulletHandler.add(0, 1.25, -3, getX() -50, y + 45);
					//MainGamePanel.eBulletHandler.add(0, 0.5, -3, getX() -50, y + 45);
					MainGamePanel.eBulletHandler.add(0, 0.1, -3, getX() -50, y + 45);
					MainGamePanel.eBulletHandler.add(0, -0.1, -3, getX() -50, y + 45);
					//MainGamePanel.eBulletHandler.add(0, -0.5, -3, getX() -50, y + 45);
					MainGamePanel.eBulletHandler.add(0, -1.25, -3, getX() -50,y + 45);
					shootType = 2;
					break;
				default:
					break;
				}
				shootTimer = 75;
				sprite.reset();
				sprite.animate();
			}

			else {
				// if enemy passes left side reset to right side
				if (x <= 1) {
					x = (int) (NULLstormActivity.getWidthVar() + 50);
					y = (int) (NULLstormActivity.getHeightVar() / 2);
					speed = -1;
				}
			}
			// move here
			if (x >= (NULLstormActivity.getWidthVar() / 2 + (NULLstormActivity
					.getWidthVar() / 3) + (NULLstormActivity.getWidthVar() / 15))) {
				x += speed;
				sprite.update(x, y, 128, 128);
			}


			if (y <= (NULLstormActivity.getHeightVar() / 2 - NULLstormActivity
					.getHeightVar() / 4)) {
				moveup = false;
			}
			if (y >= (NULLstormActivity.getHeightVar() / 2 + NULLstormActivity
					.getHeightVar() / 4)) {
				moveup = true;
			}
			if (moveup ==  true)
				yspeed = -1;
			else
				yspeed = 1;
			y += yspeed;
			sprite.update(x, y, 128, 128);
		}
	}

	public Rect getBounds() {
		return new Rect((int) getX() - Textures.enemy.getWidth() / 2,
				(int) getY() - Textures.enemy.getHeight() / 2, (int) getX()
						+ Textures.enemy.getWidth() / 2, (int) getY()
						+ Textures.enemy.getHeight() / 2);
	}

	public boolean isDead() {
		if (health <= 0)
			return true;
		return false;
	}

	public void hurt(int damage) {
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
