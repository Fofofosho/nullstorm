package pack.NULLstorm.main;

import java.util.LinkedList;

import textures.Textures;
import android.graphics.Canvas;

//makes and moves stars
public class StarHandler
{

	private LinkedList<Integer> xPos;
	private LinkedList<Integer> yPos;
	private LinkedList<Integer> speed;

	public StarHandler()
	{
		xPos = new LinkedList<Integer>();
		yPos = new LinkedList<Integer>();
		speed = new LinkedList<Integer>();
		addStars(75);
	}

	public void draw(Canvas canvas)
	{
		for (int i = 0; i < getSizeOfxPos(); i++)
		{
			canvas.drawBitmap(Textures.star, xPos.get(i) - 
					(Textures.star.getWidth() / 2), yPos.get(i) - 
					(Textures.star.getHeight() / 2), null);
		}
	}

	public void update()
	{
		for (int i = 0; i < getSizeOfxPos(); i++)
		{
			// move to new location on screen
			xPos.set(i, xPos.get(i) + speed.get(i));
			if (xPos.get(i) < 0)
			{
				setXY(i, NULLstormActivity.getWidthVar(), (int) (Math.random() * (NULLstormActivity.getHeightVar())));
			}
		}
	}

	public void addStars(int amount)
	{
		for (int i = 0; i < amount; i++)
		{
			xPos.add((int) (Math.random() * (NULLstormActivity.getWidthVar())));
			yPos.add((int) (Math.random() * (NULLstormActivity.getHeightVar())));
			speed.add((int) -((Math.random() * 4) + 5));
		}
	}

	public void remove(int i)
	{
		if (i > getSizeOfxPos() - 1)
		{
			xPos.removeLast();
			yPos.removeLast();
			speed.removeLast();
		} else if (i < 0)
		{
			xPos.remove(0);
			yPos.remove(0);
			speed.remove(0);
		} else
		{
			xPos.remove(i);
			yPos.remove(i);
			speed.remove(i);
		}
	}

	public void removeAll()
	{
		for (int i = 0; i < getSizeOfxPos(); i++)
			remove(0);
	}

	public int getSizeOfxPos()
	{
		return xPos.size();
	}
	
	public void setSpeed(double range, double factor)
	{
		for (int i = 0; i < getSizeOfxPos(); i++)
		{
			speed.set(i,((int) -((Math.random() * range) + factor)));
		}
	}

	public void setXY(int i, int x1, int y1)
	{
		xPos.set(i, x1);
		yPos.set(i, y1);
	}
}
