package textures;

import android.graphics.Rect;

public class SpriteHandler {
	private int bitWidth, bitHeight, hFrames, frames;
	private double currentFrame, rate;
	public boolean finished, centered;
	public Rect spriteRect, destRect;
	public SpriteHandler(int spriteWidth, int spriteHeight, int hFrames, int vFrames, double rate){
		this.hFrames=hFrames;
		this.rate=rate;
		bitWidth=spriteWidth/hFrames;
		bitHeight=spriteHeight/vFrames;
		frames = (int)(hFrames*vFrames); //good time to start
		spriteRect = new Rect(0,0,bitWidth,bitHeight);
		destRect = new Rect();
	}
	public void animate(){
		if (currentFrame+rate < frames) currentFrame+=rate;
		else{
			finished = true; //used to remove an animation after 1 use
			currentFrame = 0;
		}
	}
	public void update(double x, double y, int xSize, int ySize){
		//adjust sprite location
		spriteRect.top = (((int)currentFrame)/hFrames)*bitHeight;
		spriteRect.bottom = spriteRect.top + bitHeight;
		spriteRect.left = (((int)currentFrame)%hFrames)*bitWidth;
		spriteRect.right = spriteRect.left + bitWidth;
		//texture placement
		if (centered){
			destRect.top = (int)(y-(ySize/2));
			destRect.bottom = destRect.top + ySize;
			destRect.left = (int)(x-(xSize/2));
			destRect.right = destRect.left + xSize;
		}
		else{
			destRect.top = (int)y;
			destRect.bottom = destRect.top + ySize;
			destRect.left = (int)x;
			destRect.right = destRect.left + xSize;
		}
	}
	public Rect getDestination(){ return destRect; }
	public Rect getSprite(){ return spriteRect; }
	public void reset(){currentFrame=0;}
	public void center(){ centered = true; }
	public boolean isFinished(){ return finished; }
	public boolean isAnimating(){ return currentFrame > 0; }
}