package pack.NULLstorm.main;

import textures.Textures;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GUI
{
	private Paint paint;
	private String message;
	private int messageFade, mSize;
	
	public GUI() {
		message = "Tap to toggle bullets";
		messageFade = 255;
		mSize = 18;
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	public void draw(Canvas canvas)
	{
		// canvas.drawBitmap(MainThread.gamePanel.texture.gui_quit,0,0, null);
		paint.setTextSize(12);
		paint.setARGB(255, 255, 255, 255);
		// ammo
		if (MainGamePanel.player.getAmmo() > 0){
			if (MainGamePanel.player.getAmmoType() > 0){
				for (int i = 0; i < MainGamePanel.player.getAmmo() * NULLstormActivity.getWidthVar() / 100; i++)
					canvas.drawBitmap(Textures.ammo2, i, 0, null);
			} 
			else{
				for (int i = 0; i < MainGamePanel.player.getAmmo() * NULLstormActivity.getWidthVar() / 100; i++)
					canvas.drawBitmap(Textures.ammo1, i, 0, null);
			}
		}
		canvas.drawBitmap(Textures.getTexture("menubutton"), 
				(NULLstormActivity.getWidthVar()
				- (Textures.getTexture("menubutton").getWidth()) - 10 - Textures.getTexture("pause").getWidth() - 20), 
				(15), null);
		if(MainGamePanel.isPaused == false){
		canvas.drawBitmap(Textures.getTexture("pause"), 
				(NULLstormActivity.getWidthVar()
				- (Textures.getTexture("pause").getWidth()) - 10), 
				(10), null);
		}
		else{
			canvas.drawBitmap(Textures.getTexture("resume"), 
					(NULLstormActivity.getWidthVar()
					- (Textures.getTexture("resume").getWidth()) - 10), 
					(10), null);
		}
		// text
		//canvas.drawText("High Score: " + NULLstormActivity.highScore, 5, 12, paint);
		canvas.drawText("Score: " + Player.getScore(), 5, 12, paint);
		canvas.drawText("Multiplier: " + MainGamePanel.player.getMultiplier(), 5, 28, paint);
		canvas.drawText("Lives: " + MainGamePanel.player.getLives(), 5, 44, paint);
		canvas.drawText("Cash " + MainGamePanel.player.getCash(), 5, 60, paint);
		if (MainGamePanel.player.getWaveTime() > 0)
		{
		if (MainGamePanel.player.getLevel() > 0) 
			canvas.drawText("Wave Complete", (NULLstormActivity.getWidthVar() / 2) - 41, NULLstormActivity.getHeightVar() / 2, paint);
		canvas.drawText("Level: " + (MainGamePanel.player.getLevel() + 1), (NULLstormActivity.getWidthVar() / 2) - 20, (NULLstormActivity.getHeightVar() / 2) - 16, paint);
		}
		//message
		if (messageFade - 2 > 0){
			messageFade-=2;
			paint.setTextSize(mSize);
			paint.setARGB(messageFade, 255,255,255);
			int xPos = (canvas.getWidth() / 2) - (int)(paint.measureText(message)/2);
			int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
			canvas.drawText(message, xPos, yPos, paint);
		}
		else messageFade = 0;
	}
	public void setMessage(String message){
		this.message=message;
		messageFade = 255;
	}
}