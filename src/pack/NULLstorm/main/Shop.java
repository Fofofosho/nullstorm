package pack.NULLstorm.main;

import textures.Textures;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Shop {
	private boolean open;
	private int fade, width, height, playerBulletPower, playerBulletSpread, playerBulletRate, playerLives,
		newBulletPower, newBulletSpread, newBulletRate, newLives, cash, powercost, spreadcost, ratecost;
	
	private Button shopExit = new Button(Textures.shopexit, 0, 0, 26*4, 12*4,1,1);
	private Button shopBuy = new Button(Textures.shopbuy, 0, 0, 26*4, 12*4,1,1);
	//button bullet power
	private Button bulletPower = new Button(Textures.shopbulletpower, 0, 0, 70*4, 8*4,1,1);
	private Button bulletPowerAdd = new Button(Textures.shopadd, 0, 0, 8*4, 8*4,1,1);
	private Button bulletPowerSubtract = new Button(Textures.shopminus, 0, 0, 8*4, 8*4,1,1);
	//button bullet spread
	private Button bulletSpread = new Button(Textures.shopbulletspread, 0, 0, 70*4, 8*4,1,1);
	private Button bulletSpreadAdd = new Button(Textures.shopadd, 0, 0, 8*4, 8*4,1,1);
	private Button bulletSpreadSubtract = new Button(Textures.shopminus, 0, 0, 8*4, 8*4,1,1);
	//button bullet rate
	private Button bulletRate = new Button(Textures.shopbulletrate, 0, 0, 70*4, 8*4,1,1);
	private Button bulletRateAdd = new Button(Textures.shopadd, 0, 0, 8*4, 8*4,1,1);
	private Button bulletRateSubtract = new Button(Textures.shopminus, 0, 0, 8*4, 8*4,1,1);
	//button lives
	private Button lives = new Button(Textures.shoplives, 0, 0, 70*4, 8*4,1,1);
	private Button livesAdd = new Button(Textures.shopadd, 0, 0, 8*4, 8*4,1,1);
	private Button livesSubtract = new Button(Textures.shopminus, 0, 0, 8*4, 8*4,1,1);
	
	public Shop(){
		fade=255;
		powercost = 150;
		spreadcost  = 300;
		ratecost = 600;
	}
	public void draw(Canvas canvas){
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		if (width != canvas.getWidth() || height != canvas.getHeight()){
			width = canvas.getWidth();
			height = canvas.getHeight();
			//bullet power
			bulletPower.update(width/2, 32);
			bulletPowerAdd.update(width-64, 32);
			bulletPowerSubtract.update(64, 32);
			//bullet spread
			bulletSpread.update(width/2, 64 + 16);
			bulletSpreadAdd.update(width-64, 64 + 16);
			bulletSpreadSubtract.update(64, 64 + 16);
			//bullet rate
			bulletRate.update(width/2, 96 + 32);
			bulletRateAdd.update(width-64, 96 + 32);
			bulletRateSubtract.update(64, 96 + 32);
			//lives
			lives.update(width/2, 128 + 48);
			livesAdd.update(width-64, 128 + 48);
			livesSubtract.update(64, 128 + 48);
			//exit or buy
			shopExit.update(width/6, height-64);
			shopBuy.update((width/6)*5, height-64);
		}
		else{
			if (open){
				//draw the buttons
				shopExit.draw(canvas, fade);
				shopBuy.draw(canvas, fade);
				bulletPower.draw(canvas, fade);
				bulletPowerAdd.draw(canvas, fade);
				bulletPowerSubtract.draw(canvas, fade);
				bulletSpread.draw(canvas, fade);
				bulletSpreadAdd.draw(canvas, fade);
				bulletSpreadSubtract.draw(canvas, fade);
				bulletRate.draw(canvas, fade);
				bulletRateAdd.draw(canvas, fade);
				bulletRateSubtract.draw(canvas, fade);
				lives.draw(canvas, fade);
				livesAdd.draw(canvas, fade);
				livesSubtract.draw(canvas, fade);
				//show cash
				
				paint.setTextSize(32);
				paint.setARGB(255,70,255,116);
				int xPos = (width / 2) - (int)(paint.measureText("Credit(s): "+cash)/2);
				int yPos = (int) ((height-64) - ((paint.descent() + paint.ascent()) / 2)) ;
				canvas.drawText("Credit(s): "+cash, xPos, yPos, paint);
				//draw the player stats
				for (int i = 0; i < playerBulletPower; i++){
					canvas.drawRect(((width/2)+36)+(i*20), 24 + (48*0), ((width/2)+52)+(i*20), 40 + (48*0), paint);
				}
				for (int i = 0; i < playerBulletSpread; i++){
					canvas.drawRect(((width/2)+36)+(i*20), 24 + (48*1), ((width/2)+52)+(i*20), 40 + (48*1), paint);
				}
				for (int i = 0; i < playerBulletRate; i++){
					canvas.drawRect(((width/2)+36)+(i*20), 24 + (48*2), ((width/2)+52)+(i*20), 40 + (48*2), paint);
				}
				for (int i = 0; i < playerLives; i++){
					canvas.drawRect(((width/2)+36)+(i*20), 24 + (48*3), ((width/2)+52)+(i*20), 40 + (48*3), paint);
				}
				paint.setARGB(255,255,255,255);
				//draw new stats
				for (int i = playerBulletPower; i < newBulletPower; i++){
					canvas.drawRect(((width/2)+36)+(i*20), 24 + (48*0), ((width/2)+52)+(i*20), 40 + (48*0), paint);
				}
				for (int i = playerBulletSpread; i < newBulletSpread; i++){
					canvas.drawRect(((width/2)+36)+(i*20), 24 + (48*1), ((width/2)+52)+(i*20), 40 + (48*1), paint);
				}
				for (int i = playerBulletRate; i < newBulletRate; i++){
					canvas.drawRect(((width/2)+36)+(i*20), 24 + (48*2), ((width/2)+52)+(i*20), 40 + (48*2), paint);
				}
				for (int i = playerLives; i < newLives; i++){
					canvas.drawRect(((width/2)+36)+(i*20), 24 + (48*3), ((width/2)+52)+(i*20), 40 + (48*3), paint);
				}
			}
		}
	}
	public void select(int x, int y){
		if (shopExit.select(x, y))
		{
			close();
		}
		else if (shopBuy.select(x, y)){
			buy();
		}
		else if (bulletPowerAdd.select(x, y)){
			if (newBulletPower < 5 && cash > powercost){
				newBulletPower++;
				cash -= powercost;
				powercost += 100;
			}
		}
		else if (bulletPowerSubtract.select(x, y)){
			if (newBulletPower > playerBulletPower){
				newBulletPower--;
				powercost -= 100;
				cash += powercost;
			}
		}
		else if (bulletSpreadAdd.select(x, y)){
			if (newBulletSpread < 5 && cash > spreadcost){
				newBulletSpread++;
				cash -= spreadcost;
				spreadcost += 200;
			}
		}
		else if (bulletSpreadSubtract.select(x, y)){
			if (newBulletSpread > playerBulletSpread){
				newBulletSpread--;
				spreadcost -= 200;
				cash += spreadcost;
			}
		}
		else if (bulletRateAdd.select(x, y)){
			if (newBulletRate < 5 && cash > ratecost){
				newBulletRate++;
				cash -= ratecost;
				ratecost += 300;
			}
		}
		else if (bulletRateSubtract.select(x, y)){
			if (newBulletRate > playerBulletRate){
				newBulletRate--;
				ratecost -= 300;
				cash += ratecost;
			}
		}
		else if (livesAdd.select(x, y)){
			if (newLives < 5 && cash > 1000){
				newLives++;
				cash -= 1000;
			}
		}
		else if (livesSubtract.select(x, y)){
			if (newLives > playerLives){
				newLives--;
				cash += 1000;
			}
		}
	}
	public void buy(){
		MainGamePanel.player.cash = cash;
		MainGamePanel.player.damageLevel = newBulletPower;
		MainGamePanel.player.spreadLevel = newBulletSpread;
		MainGamePanel.player.shootLevel = newBulletRate;
		MainGamePanel.player.lives = newLives;
		close();
		
	}
	public void setOpen(){
		playerBulletPower = newBulletPower = MainGamePanel.player.damageLevel;
		playerBulletSpread = newBulletSpread = MainGamePanel.player.spreadLevel;
		playerBulletRate = newBulletRate = MainGamePanel.player.shootLevel;
		playerLives = newLives = MainGamePanel.player.lives;
		cash = MainGamePanel.player.cash;	
		MainGamePanel.gameState = 3;
		open = true;
	}
	public void close(){
		MainGamePanel.gameState = 1;
		changePlayerPower();
		changePlayerRate();
		open = false;
	}
	public boolean isOpen(){ return open; }
	public void changePlayerPower(){
		switch (newBulletPower) {
		case(2):
			MainGamePanel.player.setDamage(35);	
			break;
		case(3):
			MainGamePanel.player.setDamage(45);		
			break;
		case(4):
			MainGamePanel.player.setDamage(55);		
			break;
		case(5):
			MainGamePanel.player.setDamage(65);		
			break;
		}
	}
	public void changePlayerRate(){
		switch (newBulletRate) {
		case(2):
			MainGamePanel.player.shootSpeed = 12;	
			break;
		case(3):
			MainGamePanel.player.shootSpeed = 10;
			break;
		case(4):
			MainGamePanel.player.shootSpeed = 8;	
			break;
		case(5):
			MainGamePanel.player.shootSpeed = 6;	
			break;
		}
	}
}
