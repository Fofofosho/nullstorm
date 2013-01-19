package pack.NULLstorm.main;

import textures.Textures;
import android.graphics.*;
// import android.graphics.Color;
// import android.graphics.Paint;

public class Player {

	private int level;
	private static int score;
	private int highScore;
	private float x; // the X coordinate
	private float y; // the Y coordinate
	private int ammo;
	private int ammoType;
	private int health;
	private int painTime;
	private int waveTime;
	private static final int setLives = 3;
	private int damage;
	int lives = setLives;
	int damageLevel;
	int shootLevel;
	int spreadLevel;
	int cash;
	int shootSpeed;
	private int shootTimer;
	private static Player player;
	private double multiplier;
	private double xCalb, yCalb;
	
	

	public Player(int x, int y) {
		// this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		health = 50;
		damage = 25;
		shootSpeed = 14;
		multiplier = 1;
		cash = 0;
		damageLevel = 1; //set to 1
		shootLevel = 1;
		spreadLevel = 1;
		xCalb = 0;
		yCalb = 0;
	}

	public void draw(Canvas canvas) {
		if (painTime % 2 == 0 && painTime < 100) {
			canvas.drawBitmap(Textures.getTexture("player"), x
					- (Textures.getTexture("player").getWidth() / 2), y
					- (Textures.getTexture("player").getHeight() / 2), null);
			/*
			 * Paint paint = new Paint();
			 * 
			 * //health bar code if (health < 100) { paint.setColor(Color.GRAY);
			 * canvas.drawRect(x - (Textures.getTexture("enemy").getWidth() /
			 * 2), y - (Textures.getTexture("enemy").getHeight() / 2) - 16, x -
			 * (Textures.getTexture("enemy").getWidth() / 2) + 64, y -
			 * (Textures.getTexture("enemy").getHeight() / 2) - 8, paint);
			 * paint.setColor(Color.GREEN); canvas.drawRect(x -
			 * (Textures.getTexture("enemy").getWidth() / 2), y -
			 * (Textures.getTexture("enemy").getHeight() / 2) - 16, x -
			 * (Textures.getTexture("enemy").getWidth() / 2) + (64 * health) /
			 * 100, y - (Textures.getTexture("enemy").getHeight() / 2) - 8,
			 * paint); }
			 */

		}
	}

	public Rect getBounds() {
		return new Rect((int) this.getX() - Textures.player.getWidth() / 6,
				(int) this.getY() - Textures.player.getHeight() / 6,
				(int) this.getX() + Textures.player.getWidth() / 6,
				(int) this.getY() + Textures.player.getHeight() / 6);
	}

	public void update() {
		
		// check bullet
		if (painTime > 0) {
			painTime--;
			if (painTime == 100) {
				this.x = 45;
				this.y = (float) (NULLstormActivity.getHeightVar() / 2);
				MainGamePanel.player.setX((int) x);
				MainGamePanel.player.setY((int) y);
			}
		}
		// Accelerometer control
		//x
		double bigPlaysX = NULLstormActivity.getAccelX() - xCalb;
		if (bigPlaysX > 0.3 || bigPlaysX < -0.3)
			y += bigPlaysX * 2.5;
		if (y > NULLstormActivity.getHeightVar()
				- Textures.getTexture("player").getHeight())
			y = NULLstormActivity.getHeightVar()
					- Textures.getTexture("player").getHeight();
		if (y < Textures.getTexture("player").getHeight() - 25)
			y = Textures.getTexture("player").getHeight() - 25;
		//y
		double bigPlaysY = NULLstormActivity.getAccelY() - yCalb;
		if (bigPlaysY > 0.3 || bigPlaysY < -0.3)
			x += NULLstormActivity.getAccelY() * 2.5;
		if (x > NULLstormActivity.getWidthVar()
				- Textures.getTexture("player").getWidth())
			x = NULLstormActivity.getWidthVar()
					- Textures.getTexture("player").getWidth();
		if (x < Textures.getTexture("player").getWidth())
			x = Textures.getTexture("player").getWidth();
	}

	public int getLives() {
		return lives;
	}
	
	public void setCalb(double x)
	{
		xCalb = x;
	}

	public void removeLives() {
		if (painTime <= 0) {
			MainGamePanel.effects.addExplosion(x, y, 64, 0);
			MainGamePanel.audio.playSound(2);
			multiplier = 1;

			lives -= 1;
			
			if (lives == 0) {
				MainGamePanel.audio.stopAll();
				setHighScore(score);
				MainGamePanel.gameState = 2;
				MainGamePanel.bg.setSpeed(2, .3);
			}
		}
	}
	
	public void addLives(int amount)
	{
		lives += amount;
	}

	public void heal(int i) {
		health += i;
		if (health > 100)
			health = 100;
	}

	public void reset() {
		health = 100;
		score = 0;
		level = 0;
		ammo = 0;
		ammoType = 0;
		lives = Player.setLives;
		x = NULLstormActivity.getWidthVar() / 2;
		y = (int) (NULLstormActivity.getHeightVar() / 1.5);
		multiplier = 1.0;
		damageLevel = 1; //set to 1
		shootLevel = 1;
		cash = 0;
		spreadLevel = 1;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void addLevel() {
		level++;
	}

	public static int getScore() {
		return score;
	}

	public void setScore(int newScore) {
		score = newScore;
	}

	public void addScore(int i) {
		score += i * multiplier;
	}

	public int getAmmo() {
		return ammo;
	}

	public void subtractAmmo(int i) {
		ammo -= i;
		if (ammo <= 0)
			ammoType = 0;
	}

	public void setAmmo(int i, int z) {
		ammoType = i;
		ammo = z;
	}

	public int getHealth() {
		return health;
	}

	public int getAmmoType() {
		return ammoType;
	}

	public void setWaveTime(int i) {
		waveTime = i;
	}

	public int getWaveTime() {
		return waveTime;
	}

	public void tick() {
		if (waveTime > 0)
			waveTime--;
	}

	public int getPainTime() {
		return painTime;
	}

	public void setPainTime(int i) {
		painTime = i;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int hs) {
		if (hs > highScore)
			highScore = hs;
	}

	public void setDamage(int i) {
		this.damage = i;
	}

	public int getDamage() {
		return damage;
	}

	public void resetShootTimer() {
		shootTimer = shootSpeed;
	}

	public void setShootRate(int time) {
		shootSpeed = time;
	}

	public int getShootTimer() {
		return shootTimer;
	}

	public void incrementShootTimer() {
		shootTimer -= 1;
	}

	public void ShootUpdate() {
		if (painTime <= 100)
			shootTimer -= 1;
		if (shootTimer <= 0) {
			MainGamePanel.audio.playSound(0);
			switch(spreadLevel)
			{
			case 1:
				MainGamePanel.bulletHandler.add(this.getAmmoType(), 0, 10,
						this.getX(), this.getY() - 3);
				break;
			case 2:
				MainGamePanel.bulletHandler.add(0, 0, 10,
						this.getX(), this.getY() + 10,3);
				MainGamePanel.bulletHandler.add(0, 0, 10,
						this.getX(), this.getY() - 10,3);
				break;
			case 3:
				MainGamePanel.bulletHandler.add(1, 0, 10,
						this.getX(), this.getY() + 10,4);
				MainGamePanel.bulletHandler.add(2, 0, 10,
						this.getX(), this.getY() - 10,4);
				break;
			case 4:
				MainGamePanel.bulletHandler.add(this.getAmmoType(), 5, 10,
						this.getX(), this.getY() - 3);
				MainGamePanel.bulletHandler.add(1, 0, 10,
						this.getX(), this.getY() + 10,5);
				MainGamePanel.bulletHandler.add(2, 0, 10,
						this.getX(), this.getY() - 10,5);
				MainGamePanel.bulletHandler.add(this.getAmmoType(), -5, 10,
						this.getX(), this.getY() - 3);
				break;
			case 5:

				MainGamePanel.bulletHandler.add(1, 0, 10,
						this.getX(), this.getY(), 5);
				MainGamePanel.bulletHandler.add(2, 0, 10,
						this.getX(), this.getY() , 5);
				MainGamePanel.bulletHandler.add(1, 0, 10,
						this.getX(), this.getY() + 10, 20);
				MainGamePanel.bulletHandler.add(2, 0, 10,
						this.getX(), this.getY() + 10, 20);
				break;
			}
			shootTimer = shootSpeed;
		}
	}
	
	

	public void initFire() {
		shootTimer = 0;
	} // instantly begin firing

	public static Player getPlayer() {
		return player;
	}

	public void incMultiplier(double amount) {
		multiplier = multiplier + .10;
		multiplier = this.round(multiplier, 1);

	}
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public int getCash() {
		return cash;
	}

	public void addCash(int amount) {
		cash += amount;
	}

	public void removeCash(int amount) {
		cash -= amount;
	}

	public void setCash(int amount) {
		cash = amount;
	}
	
	public int getSpreadLevel(){
		return spreadLevel;
	}
	
	public void setSpreadLevel(int amount){
		spreadLevel = amount;
	}
}
