package pack.NULLstorm.main;

import java.util.LinkedList;

import textures.Textures;

import android.graphics.Canvas;
import android.graphics.Rect;

public class BulletHandler
{
	LinkedList<Bullet> bullet;

	public BulletHandler()
	{
		bullet = new LinkedList<Bullet>();
	}

	public void update()
	{
		//for each bullet
		for (int i = 0; i < bullet.size(); i++)
		{
			//update
			bullet.get(i).update();
			
			//check collision with enemies
			for (int j = 0; j < MainGamePanel.enemyHandler.size(); j++)
			{
				//if collision
				/*
				if (Math.abs(bullet.get(i).getX() - EnemyHandler.getEnemies().get(j).getX()) < Textures.bullet.getWidth()
						&& Math.abs(bullet.get(i).getY()
								- EnemyHandler.getEnemies().get(j).getY()) < Textures.bullet.getHeight())
								*/
				if (Rect.intersects(this.getBounds(i),
						EnemyHandler.getEnemies().get(j).getBounds())) 

				
				{
					//remove bullet and damage enemy
					bullet.get(i).setAlive(false);
					MainGamePanel.enemyHandler.hurt(MainGamePanel.player.getDamage(),j);
				}
			}
			//if bullet is not alive
			if (!bullet.get(i).isAlive())
				remove(i);
		}
	}
	
	public Rect getBounds(int i){
		return new Rect((int)bullet.get(i).getX() - Textures.bullet.getWidth()/2,
				(int)bullet.get(i).getY() - Textures.bullet.getHeight()/2,
				(int)bullet.get(i).getX() + Textures.bullet.getWidth()/2,
				(int)bullet.get(i).getY() + Textures.bullet.getHeight()/2
				);
	}
	
	public void draw(Canvas canvas)
	{
		for (int i = 0; i < bullet.size(); i++)
		{
			canvas.drawBitmap(Textures.bullet,
					bullet.get(i).getX() - (Textures.bullet.getWidth() / 2), bullet.get(i).getY()
							- (Textures.bullet.getHeight() / 2), null);		}
	}

	//add bullets to the bullet handler list
	public void add(int type, double xDirection, double speed, double x,
			double y)
	{
		bullet.add(new Bullet(type, xDirection, speed, x, y));
	}
	public void add(int type, double xDirection, double speed, double x,
			double y, double radius) {
		bullet.add(new Bullet(type, xDirection, speed, x, y, radius));
	}

	//remove bullet at position y
	public void remove(int i)
	{
		if (i > size() - 1)
			bullet.removeLast();
		else if (i < 0)
			bullet.remove(0);
		else
			bullet.remove(i);
	}

	//remove all bullets
	public void removeAll()
	{
		int size = bullet.size();
		for (int i = 0; i < size; i++)
			bullet.remove(0);
	}

	//number of bullets
	public int size()
	{
		return bullet.size();
	}
}
