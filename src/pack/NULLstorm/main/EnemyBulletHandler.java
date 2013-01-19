package pack.NULLstorm.main;

import java.util.LinkedList;

import textures.Textures;

import android.graphics.Canvas;
import android.graphics.Rect;

public class EnemyBulletHandler {

	LinkedList<Bullet> bullet;

	public EnemyBulletHandler() {
		bullet = new LinkedList<Bullet>();
	}

	public void update() {
		// for each bullet
		for (int i = 0; i < bullet.size(); i++) {
			// update
			bullet.get(i).update();

			// check collision with player

			// if collision
			/*
			 * if (Math.abs((bullet.get(i).getX() + Textures.bullet2
			 * .getWidth()) - (MainGamePanel.player.getX())) < Textures.bullet2
			 * .getWidth()/2 && Math.abs((bullet.get(i).getY() +
			 * Textures.bullet2 .getHeight()) - (MainGamePanel.player.getY())) <
			 * Textures.bullet2 .getHeight()/2) { // remove bullet and damage
			 * enemy bullet.get(i).setAlive(false);
			 * MainGamePanel.player.removeLives();
			 * MainGamePanel.player.setPainTime(100); }
			 */

			if (Rect.intersects(this.getBounds(i),
					MainGamePanel.player.getBounds())) {
				if (MainGamePanel.player.getPainTime() <= 0) {
					bullet.get(i).setAlive(false);
					MainGamePanel.player.removeLives();
					MainGamePanel.player.setPainTime(200);
				}
			}
			// if bullet is not alive
			if (!bullet.get(i).isAlive())
				remove(i);
		}
	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < bullet.size(); i++) {

			if (bullet.get(i).getType() == 0) {
				canvas.drawBitmap(Textures.bullet2, bullet.get(i).getX()
						- (Textures.bullet2.getWidth() / 2), bullet.get(i)
						.getY() - (Textures.bullet2.getHeight() / 2), null);
			}
		}
	}

	public Rect getBounds(int i) {
		return new Rect((int) bullet.get(i).getX()
				- Textures.bullet2.getWidth() / 4, (int) bullet.get(i).getY()
				- Textures.bullet2.getHeight() / 4, (int) bullet.get(i).getX()
				+ Textures.bullet2.getWidth() / 4, (int) bullet.get(i).getY()
				+ Textures.bullet2.getHeight() / 4);
	}

	// add bullets to the bullet handler list
	public void add(int type, double xDirection, double speed, double x,
			double y) {
		bullet.add(new Bullet(type, xDirection, speed, x, y));
	}
	public void add(int type, double xDirection, double speed, double x,
			double y, double radius) {
		bullet.add(new Bullet(type, xDirection, speed, x, y, radius));
	}

	// remove bullet at position y
	public void remove(int i) {
		if (i > size() - 1)
			bullet.removeLast();
		else if (i < 0)
			bullet.remove(0);
		else
			bullet.remove(i);
	}

	// remove all bullets
	public void removeAll() {
		int size = bullet.size();
		for (int i = 0; i < size; i++)
			bullet.remove(0);
	}

	// number of bullets
	public int size() {
		return bullet.size();
	}

	public void setSpeed(double speed) {
		int size = bullet.size();
		for (int i = 0; i < size; i++)
			bullet.get(i).setSpeed(speed);
	}
}
