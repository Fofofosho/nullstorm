package pack.NULLstorm.main;

import java.util.LinkedList;
import textures.Textures;
import android.graphics.Canvas;

public class EffectHandler {
	LinkedList<Explosion> exploList;
	public EffectHandler(){
		exploList = new LinkedList<Explosion>(); //initiate explosion vars
	}
	public void draw(Canvas canvas){
		for (int i = 0; i < explosionSize(); i++){
			exploList.get(i).update();
			if (!exploList.get(i).isActive()) removeExplosion(i);
			else{
				canvas.drawBitmap(Textures.explosion, 
				exploList.get(i).sprite.spriteRect,
				exploList.get(i).sprite.destRect, null);
			}
		}
	}
	public int explosionSize(){ return exploList.size(); }
	public void addExplosion(double x1, double y1, int size, int type){
		//if we want to add a different kind of explosion then *type*
		//has the capability of calling a different kind of explosion using integers
		
		switch(type)
		{
			case 0:
				exploList.add(new Explosion(x1, y1, size));
				break;
				
			//case 1:
				//exploList.add(new "A different explosion"(x1, y1));
				//break;
				
			default:
				exploList.add(new Explosion(x1, y1, size));
				break;
		}
		
	}
	public void removeExplosion(int i){
		exploList.remove(i);
	}
	public void removeAllExplosions(){
		int size = explosionSize();
		for (int i = 0; i < size; i++){
			removeExplosion(0);
		}
	}
}
