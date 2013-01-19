package pack.NULLstorm.main;

import textures.Textures;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class BossEnemy2 extends IEnemy{

	private int x;
	private int y;
	private double speed;
	private int health;
	private int shootTimer;
	private int shootType;
	private double multiplierAmount;
	private int cashAmount;

	public BossEnemy2(int x, int y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		score = 1000;
		health = 10000;
		shootTimer = 75;
		shootType = 1;
		type = 1;
		multiplierAmount = 1;
		cashAmount = 1000;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(Textures.enemy,
				this.getX() - (Textures.enemy.getWidth() / 2), this.getY()
						- (Textures.enemy.getHeight() / 2), null);
		
		Paint paint = new Paint();

		// health bar code
			paint.setColor(Color.RED);
			canvas.drawRect(
					NULLstormActivity.getHeightVar() / 10,
					NULLstormActivity.getHeightVar() / 20,
					NULLstormActivity.getHeightVar() / 10  + (64 * 10000)/2000, 
					NULLstormActivity.getHeightVar() / 20 + 16, 
					paint);
			paint.setColor(Color.GREEN);
			canvas.drawRect(
					NULLstormActivity.getHeightVar() / 10,
					NULLstormActivity.getHeightVar() / 20,
					NULLstormActivity.getHeightVar() / 10  + (64 * health)/2000, 
					NULLstormActivity.getHeightVar() / 20 + 16, 
					paint);
	}

	public void update() {

		// shoot things stuff
		if (x >= 150) {
			shootTimer -= 1;
			if (shootTimer <= 0 && health >= 7000) {
				switch (shootType) {
				case (1):
					MainGamePanel.eBulletHandler.add(0, 1.5, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 1, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0.3, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler
							.add(0, -0.3, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -1, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler
							.add(0, -1.5, -3, getX() + 3, y);
					shootType = 2;
					break;
				case (2):
					MainGamePanel.eBulletHandler
							.add(0, 1.25, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0.5, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0.1, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler
							.add(0, -0.1, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler
							.add(0, -0.5, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -1.25, -3, getX() + 3,
							y);
					shootType = 1;
					break;
				default:
					break;
				}
				shootTimer = 75;
			} else if (shootTimer <= 0 && health >= 3000) {
				switch (shootType) {
				case (1):
					MainGamePanel.eBulletHandler.add(0, .7, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, .4, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.4, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.7, -3, getX() + 3, y);
					shootType = 2;
					break;
				case (2):
					MainGamePanel.eBulletHandler.add(0, .6, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, .3, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.3, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.6, -3, getX() + 3, y);
					shootType = 3;
					break;
				case (3):
					MainGamePanel.eBulletHandler.add(0, .5, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, .2, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.2, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.5, -3, getX() + 3, y);
					shootType = 4;
					break;
				case (4):
					MainGamePanel.eBulletHandler.add(0, .4, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, .1, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.1, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.4, -3, getX() + 3, y);
					shootType = 5;
					break;
				case (5):
					MainGamePanel.eBulletHandler.add(0, .5, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, .2, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.2, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.5, -3, getX() + 3, y);
					shootType = 6;
					break;
				case (6):
					MainGamePanel.eBulletHandler.add(0, .6, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, .3, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, 0, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.3, -3, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.6, -3, getX() + 3, y);
					shootType = 1;
					break;
				default:
					break;
				}
				shootTimer = 50;
			}
			else if (shootTimer <= 0 && health >= 200) {
				switch (shootType) {
				case (1):
					MainGamePanel.eBulletHandler.add(0, .5, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.5, -4, getX() + 3, y);
					shootType = 2;
					break;
				case (2):
					MainGamePanel.eBulletHandler.add(0, .4, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.4, -4, getX() + 3, y);
					shootType = 3;
					break;
				case (3):
					MainGamePanel.eBulletHandler.add(0, .3, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.3, -4, getX() + 3, y);
					shootType = 4;
					break;
				case (4):
					MainGamePanel.eBulletHandler.add(0, .2, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.2, -4, getX() + 3, y);
					shootType = 5;
					break;
				case (5):
					MainGamePanel.eBulletHandler.add(0, .1, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.1, -4, getX() + 3, y);
					shootType = 6;
					break;
				case (6):
					MainGamePanel.eBulletHandler.add(0, .0, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.0, -4, getX() + 3, y);
					shootType = 7;
					break;
				case (7):
					MainGamePanel.eBulletHandler.add(0, .1, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.1, -4, getX() + 3, y);
					shootType = 8;
					break;
				case (8):
					MainGamePanel.eBulletHandler.add(0, .2, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.2, -4, getX() + 3, y);
					shootType = 9;
					break;
				case (9):
					MainGamePanel.eBulletHandler.add(0, .3, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.3, -4, getX() + 3, y);
					shootType = 10;
					break;
				case (10):
					MainGamePanel.eBulletHandler.add(0, .4, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.4, -4, getX() + 3, y);
					shootType = 11;
					break;
				case (11):
					MainGamePanel.eBulletHandler.add(0, .5, -4, getX() + 3, y);
					MainGamePanel.eBulletHandler.add(0, -.5, -4, getX() + 3, y);
					shootType = 1;
					break;
					
				default:
					break;
				}
				shootTimer = 20;
			}
			
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
		if (x >= (NULLstormActivity.getWidthVar() / 2 + NULLstormActivity
				.getWidthVar() / 3)) {
			x += speed;
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
