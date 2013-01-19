package pack.NULLstorm.main;

import textures.SpriteHandler;
import textures.Textures;

public class Explosion {
	private double x, y;
	private int spriteWidth, spriteHeight, width, height;
	private boolean active;
	SpriteHandler sprite;
	public Explosion(double x, double y, int size){
		active = true;
		this.x=x;
		this.y=y;
		width = height = size;
		spriteWidth = Textures.explosion.getWidth();
		spriteHeight = Textures.explosion.getHeight();
		sprite = new SpriteHandler(spriteWidth, spriteHeight, 4, 1, 0.2); //4 frames
		sprite.center();
	}
	public void update(){
		sprite.animate();
		sprite.update(x,y,width,height); //center it bith
		if (sprite.isFinished()) active = false;
	}
	public double getX(){ return x; }
	public double getY(){ return y; }
	public int getWidth(){ return spriteWidth; }
	public int getHeight(){ return spriteHeight; }
	public boolean isActive(){ return active; }
}
