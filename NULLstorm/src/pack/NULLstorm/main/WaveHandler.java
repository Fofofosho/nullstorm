package pack.NULLstorm.main;

public class WaveHandler {

	private int eNumber, eScale, currentWave, delay; 
	int healthscale;
	private boolean notAllSpawned;
	private double speed;
	boolean shopRecent;
	static EnemyBulletHandler enemyBulletHandler = new EnemyBulletHandler();

	public WaveHandler() {
		currentWave = 1;
		eScale = 2;
		eNumber = 3;
		delay = 200;
		speed = 5;
		notAllSpawned = false;
		shopRecent = false;
		healthscale = 1;
	}
	public void update() {
		if (currentWave != MainGamePanel.player.getLevel()) {
			if ((currentWave % 10) == 0 && currentWave > 6)
			{
					healthscale++;
			}
			if (currentWave == 4 || currentWave == 19) {
				MainGamePanel.enemyHandler.reset();
				MainGamePanel.enemyHandler.add(10, 1);
			}
			
			else if (currentWave == 9 || currentWave == 24) {
				MainGamePanel.enemyHandler.reset();
				MainGamePanel.enemyHandler.add(11, 1);
			} 
			
			else if (currentWave == 14 || currentWave == 29) {
				MainGamePanel.enemyHandler.reset();
				MainGamePanel.enemyHandler.add(12, 1);
			}
			
			else {
				if (eNumber - 7 > 0)
				{
					MainGamePanel.enemyHandler.add(2, eNumber - 7);
				}
				if (eNumber - 13 > 0)
				{
					MainGamePanel.enemyHandler.add(3, (1));
				}
					MainGamePanel.enemyHandler.add(1, eNumber);
					eNumber += eScale;
			}
			currentWave = MainGamePanel.player.getLevel();
		}
		/*

			if (notAllSpawned == true
					&& MainGamePanel.enemyHandler.size() < eNumber / 3
					&& currentWave  != 6 && currentWave != 11) {
				notAllSpawned = false;
				MainGamePanel.enemyHandler.add(1, eNumber / 2);
			}*/

			if (MainGamePanel.enemyHandler.size() < 1 && MainGamePanel.eBulletHandler.size() < 1) {
				//If there are no more enemies on screen and no more bullets
				//the background will go into "warp speed"
				
				/* 
				 * if (MainGamePanel.player.getWaveTime() > 0)
				 * MainGamePanel.player.tick(); else {
				 * MainGamePanel.player.setWaveTime(150); if
				 * ((MainGamePanel.player.getLevel() + 1) % 5 == 0)
				 * MainGamePanel.item.spawn(); } if
				 * (MainGamePanel.player.getWaveTime() <= 0) {
				 */
				//if (currentWave > 0)
				if (MainGamePanel.player.getLevel() % 5 == 0 && MainGamePanel.player.getLevel() > 1 || MainGamePanel.player.getLevel() == 3 )
				{
					if (shopRecent != true)
					{
					MainGamePanel.shop.setOpen();
					shopRecent = true;
					}
					
				}
				MainGamePanel.gui.setMessage("Wave "+(currentWave+1)+" Complete!");
				enemyBulletHandler.removeAll();
				if (delay > 0) {
					delay = delay - 1;
					MainGamePanel.bg.setSpeed(4, (int) speed);
					speed += .1;
					MainGamePanel.eBulletHandler.setSpeed(-speed);
				} else if (speed > 5) {
					speed -= .5;
					MainGamePanel.bg.setSpeed(4, (int) speed);
				} else {
					MainGamePanel.bg.setSpeed(4, 5);
					delay = 200;
					speed = 5;
					notAllSpawned = true;
					MainGamePanel.player.addLevel();
					//if (currentWave > 0)
					MainGamePanel.gui.setMessage("Wave "+(currentWave+2)+" Starting");
					shopRecent = false;
				}
			}
		}
	

	public void reset() {
		eScale = 2;
		eNumber = 2;
		delay = 200;
		speed = 5;
		notAllSpawned = false;
		currentWave = 1;
		MainGamePanel.bg.setSpeed(4, (int) speed);

	}
}
