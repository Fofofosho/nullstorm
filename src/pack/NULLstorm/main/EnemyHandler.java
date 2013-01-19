package pack.NULLstorm.main;

import java.util.LinkedList;

import textures.Textures;

import android.graphics.Canvas;
import android.graphics.Rect;
import audio.AudioHandler;

public class EnemyHandler {
	private boolean dasBootBoss = false;
	
	// This classes acts as a means of handling all enemies on screen
	private static LinkedList<IEnemy> enemies;

	public EnemyHandler() {
		enemies = new LinkedList<IEnemy>();
	}

	public static LinkedList<IEnemy> getEnemies() {
		return enemies;
	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(canvas);
		}
	}

	public void update() {
		// Update method
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();

			// If an enemy is dead, we remove him from the Linked List
			if (enemies.get(i).isDead()) {
				MainGamePanel.audio.playSound(2);
				MainGamePanel.player.addScore(enemies.get(i).getScore());
				MainGamePanel.player.addCash(enemies.get(i).getCash());
				MainGamePanel.player.incMultiplier(enemies.get(i).getMultiplier());
				MainGamePanel.effects.addExplosion(enemies.get(i).getX(), enemies.get(i).getY(), 64, 0);
				remove(i);

			} else {

				// Check collision
				/*
				 * if (Math.abs(enemies.get(i).getX() -
				 * MainGamePanel.player.getX()) < Textures.enemy .getWidth() -
				 * 14 && Math.abs(enemies.get(i).getY() -
				 * MainGamePanel.player.getY()) < Textures.enemy .getHeight() -
				 * 14) { if (MainGamePanel.player.getPainTime() <= 0) {
				 * remove(i); MainGamePanel.player.removeLives();
				 * MainGamePanel.player.setPainTime(100); } }
				 */

				if (Rect.intersects(this.getBounds(i),
						MainGamePanel.player.getBounds())) {
					if (MainGamePanel.player.getPainTime() <= 0) {
						if (enemies.get(i).type != 1)
							remove(i);
						MainGamePanel.player.removeLives();
						MainGamePanel.player.setPainTime(200);
					}

				}
			}
		}

	}

	public Rect getBounds(int i) {
		return enemies.get(i).getBounds();
	}

	public void add(int type, int count) {
		for (int i = 0; i < count; i++) {
			switch (type) {

			// Tests to make sure spawner does not spawn in same location
			case (1):

				// Try to create an enemy
				int x = (int) ((NULLstormActivity.getWidthVar() + 30) + (Math.random() * 500));
				int y = (int)(30 + (Math.random() * ((NULLstormActivity.getHeightVar() - Textures.enemy.getHeight()) - 30)));		
				enemies.add(new Enemy1(x,y, -(Math.abs(Math.random() * 2))));

				// See if there is overlap with the newly created enemy
				// If there is, remove the enemy and attempt to create a new
				// one...
				for (int shipIndex = 0, check = 0; shipIndex < enemies.size(); shipIndex++) {

					if (shipIndex != enemies.size() - 1) {
						if (Rect.intersects(getBounds(shipIndex),
								getBounds(enemies.size() - 1))) {
							//MainGamePanel.effects.addExplosion(50, 50, 64, 0);// for
																	// debugging
							remove(enemies.size() - 1);
							x = (int) ((NULLstormActivity.getWidthVar() + 30) + (Math.random() * 500));
							y = (int)(30 + (Math.random() * ((NULLstormActivity.getHeightVar() - Textures.enemy.getHeight()) - 30)));		
							enemies.add(new Enemy1(x,y, -(Math.abs(Math.random() * 2))));
							shipIndex = 0;
							check++;
						}
					}
					// If you have run this test 5 times and it fails then dont
					// add a new ship
					if (check == 30) {
						remove(size()-1);
						break;
					}
				}
				break;
			case 2:
				// Try to create an enemy
				x = (int) ((NULLstormActivity.getWidthVar() + 30) + (Math.random() * 500));
				y = (int)(30 + (Math.random() * ((NULLstormActivity.getHeightVar() - Textures.enemy.getHeight()) - 30)));		
				enemies.add(new Enemy2(x,y, -(Math.abs(Math.random() * 2))));

				// See if there is overlap with the newly created enemy
				// If there is, remove the enemy and attempt to create a new
				// one...
				for (int shipIndex = 0, check = 0; shipIndex < enemies.size(); shipIndex++) {

					if (shipIndex != enemies.size() - 1) {
						if (Rect.intersects(getBounds(shipIndex),
								getBounds(enemies.size() - 1))) {
							//MainGamePanel.effects.addExplosion(50, 50, 64, 0);// for
																	// debugging
							remove(enemies.size() - 1);
							x = (int) ((NULLstormActivity.getWidthVar() + 30) + (Math.random() * 500));
							y = (int)(30 + (Math.random() * ((NULLstormActivity.getHeightVar() - Textures.enemy.getHeight()) - 30)));		
							enemies.add(new Enemy2(x,y, -(Math.abs(Math.random() * 2))));
							shipIndex = 0;
							check++;
						}
					}
					// If you have run this test 5 times and it fails then dont
					// add a new ship
					if (check == 30) {
						remove(size()-1);
						break;
					}
				}
				break;
			case 3:
				// Try to create an enemy
				x = (int) ((NULLstormActivity.getWidthVar() + 30) + (Math.random() * 500));
				y = (int)(30 + (Math.random() * ((NULLstormActivity.getHeightVar() - Textures.enemy.getHeight()) - 30)));		
				enemies.add(new Enemy3(x,y, -(Math.abs(Math.random() * 2))));

				// See if there is overlap with the newly created enemy
				// If there is, remove the enemy and attempt to create a new
				// one...
				for (int shipIndex = 0, check = 0; shipIndex < enemies.size(); shipIndex++) {

					if (shipIndex != enemies.size() - 1) {
						if (Rect.intersects(getBounds(shipIndex),
								getBounds(enemies.size() - 1))) {
							//MainGamePanel.effects.addExplosion(50, 50, 64, 0);// for
																	// debugging
							remove(enemies.size() - 1);
							x = (int) ((NULLstormActivity.getWidthVar() + 30) + (Math.random() * 500));
							y = (int)(30 + (Math.random() * ((NULLstormActivity.getHeightVar() - Textures.enemy.getHeight()) - 30)));		
							enemies.add(new Enemy3(x,y, -(Math.abs(Math.random() * 2))));
							shipIndex = 0;
							check++;
						}
					}
					// If you have run this test 5 times and it fails then dont
					// add a new ship
					if (check == 30) {
						remove(size()-1);
						break;
					}
				}
				break;

			// Cases 2-9 cover regular enemies
			// Cases 10-19 cover bosses
			case (10):
				enemies.add(new BossEnemy((int) (NULLstormActivity
						.getWidthVar() + 50), (int) (NULLstormActivity
						.getHeightVar() / 2) - Textures.enemy.getHeight() / 2,
						-1));
				MainGamePanel.audio.playMusic(R.raw.auroraaudioboss);
				dasBootBoss = true;
				break;
				
			case (11):
				enemies.add(new BossEnemy2((int) (NULLstormActivity
						.getWidthVar() + 50), (int) (NULLstormActivity
						.getHeightVar() / 2) - Textures.enemy.getHeight() / 2,
						-1));
				MainGamePanel.audio.playMusic(R.raw.auroraaudioboss);
				dasBootBoss = true;
				break;
				
			case (12):
				enemies.add(new BossEnemy3((int) (NULLstormActivity
						.getWidthVar() + 50), (int) (NULLstormActivity
						.getHeightVar() / 2) - Textures.enemy.getHeight() / 2,
						-1));
				MainGamePanel.audio.playMusic(R.raw.auroraaudioboss);
				dasBootBoss = true;
				break;
			}
		}

	}

	public void hurt(int damage, int i) {
		enemies.get(i).hurt(damage);
	}

	public void reset() {
		enemies = new LinkedList<IEnemy>();
	}

	private void remove(int i) {
		if (i > size() - 1)
		{
			enemies.removeLast();
			if(dasBootBoss)
				MainGamePanel.audio.stopMusic();
		}
		else{
			enemies.remove(i);
			if(dasBootBoss)
				MainGamePanel.audio.stopMusic();
		}
			
	}

	public int size() {
		return enemies.size();
	}

}
