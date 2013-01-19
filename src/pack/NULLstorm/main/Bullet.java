package pack.NULLstorm.main;

// import android.graphics.Canvas;

public class Bullet {
	private boolean alive;
	private double speed = 10;
	private double xPos, yPos;
	private double xDirection; // A value of 1 will make it go towards the right
	private double mp8 = Math.PI / 10; // Pi over 180 is OK
	// private double mp8 = Math.PI / 180;
	// private double spacing = 0.5;
	private double radius;
	private double theta;
	private int type; // Normal: 0; Spiral: 1

	// constructor
	public Bullet(int type, double xDirection, double speed, double x, double y) {
		this.type = type;
		this.xDirection = xDirection;
		this.xPos = x;
		this.yPos = y;
		alive = true;
		this.speed = speed;
		radius = 10;
	}
	public Bullet(int type, double xDirection, double speed, double x, double y, double radius) {
		this.type = type;
		this.xDirection = xDirection;
		this.xPos = x;
		this.yPos = y;
		alive = true;
		this.speed = speed;
		this.radius = radius;
	}

	// update
	public void update() {
		if (type == 0) { // normal
			yPos += xDirection;
			xPos += speed;
		}
		if (type == 1) { // normal
			this.checkCos();
		}
		if (type == 2) { // normal
			this.checkNegCos();
		}
		
		if (xPos < 0 || xPos > NULLstormActivity.getWidthVar() + 10)
			alive = false; // out of range
	}
/*
	public void draw(Canvas canvas) {
		canvas.drawBitmap(Textures.bullet,
				this.getX() - (Textures.bullet.getWidth() / 2), this.getY()
						- (Textures.bullet.getHeight() / 2), null);
	}
*/
	public int getX() {
		return (int) xPos;
	}

	public int getY() {
		return (int) yPos;
	}

	public void reset() {

	}

	public void setSpeed(double i) {
		speed = i;
	}

	public double getSpeed() {
		return speed;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/*public void checkSpiral() { // spiral formula
		theta += mp8;
		radius = spacing * theta;
		xPos = Math.cos(theta) * radius + xPos;
		yPos = Math.sin(theta) * radius + yPos - 1;
	}*/
	
	public int getType () {
		return type;
	}
	
	public void checkCos(){
		theta += mp8;
		xPos += speed;
		yPos += Math.cos(theta) * radius;
	}
	public void checkNegCos(){
		theta += mp8;
		xPos += speed;
		yPos -= Math.cos(theta) * radius;
	}
}
