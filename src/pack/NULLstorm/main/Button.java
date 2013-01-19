package pack.NULLstorm.main;

import java.util.LinkedList;

import textures.SpriteHandler;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Button {
	boolean hide;
	Bitmap button;
	SpriteHandler buttonSprite;
	int x, y, xSize, ySize, 
		width, height, padding;
	LinkedList<Integer> pX,pY;
	
	public Button(Bitmap button, int x, int y, int xSize, int ySize, int hFrame, int vFrame){
		this.button=button;
		buttonSprite = new SpriteHandler(button.getWidth(), button.getHeight(), hFrame, vFrame, 0.5); //4 frames
		this.x=x;
		this.y=y;
		this.xSize=xSize;
		this.ySize=ySize;
		buttonSprite.center();
		buttonSprite.update(x,y,xSize,ySize);
		pX = new LinkedList<Integer>();
		pY = new LinkedList<Integer>();
	}
	public void update(int x, int y){
		if (!hide){
			this.x=x;
			this.y=y;
			buttonSprite.update(x, y, xSize, ySize);
		}
	}
	public void draw(Canvas canvas, int fade){
		Paint paint = new Paint();
		paint.setARGB(fade,178,255,196);
		if (!hide) canvas.drawBitmap(button, buttonSprite.spriteRect, buttonSprite.destRect, paint);
		else {
			for (int i = 0; i < pX.size(); i++){
				canvas.drawRect(pX.get(i),pY.get(i),pX.get(i)+4,pY.get(i)+4, paint);
				if (pX.get(i) > 0){
					pX.set(i, pX.get(i)-5);
					pY.set(i, pY.get(i)+((int)(Math.random()*9)-4));
				}
				else{
					pX.remove(i);
					pY.remove(i);
				}	
			}
		}
	}
	public boolean select(int x1, int y1){
		padding = 24; //makes buttons easier to tap by expanding the hitbox
		if (Math.abs(x1-x) < ((xSize/2)+padding) && Math.abs(y1-y) < ((ySize/2)+padding)) return true;
		else return false;
	}
	public void hide(){
		if (!hide){
			for (int i=0; i<40; i++){
				pX.add(x+((int)(Math.random()*xSize)-(xSize/2)));
				pY.add(y+((int)(Math.random()*ySize)-(ySize/2)));
			}
			hide = true;
		}
	}
	public void reveal(){ hide = false; }
}
