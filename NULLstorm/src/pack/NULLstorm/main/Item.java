package pack.NULLstorm.main;

import textures.Textures;
import android.graphics.Canvas;

public class Item
{
	private double x, y, xDirection;
	private boolean check;

	public Item()
	{
		y = 600;
	}

	public void draw(Canvas canvas)
	{
		if (check)
		{
			canvas.drawBitmap(
					Textures.item,
					getX() - (Textures.item.getWidth() / 2),
					getY()
							- (Textures.item.getHeight() / 2),
					null);
		}
	}

	public void update()
	{
		if (y < 600)
			check = true;
		else
			check = false;
		if (check)
		{
			x += xDirection;
			y += 4;

			if (Math.abs(getX() - MainGamePanel.player.getX()) < 32
					&& Math.abs(getY() - MainGamePanel.player.getY()) < 32)
			{
				int ammoType = (int) (Math.random() * 2);
				MainGamePanel.player.setAmmo(ammoType, 100);
				MainGamePanel.player.heal(25);
				y = 600;// do not check anymroe
			}
		}
	}

	public void spawn()
	{
		xDirection = (Math.random() * ((int) (Math.random() * 5) - 2));
		x = 160;
		y = -64;
	}

	public int getX()
	{
		return (int) x;
	}

	public int getY()
	{
		return (int) y;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public double getxDirection()
	{
		return xDirection;
	}

	public void setxDirection(int xDirection)
	{
		this.xDirection = xDirection;
	}
}
