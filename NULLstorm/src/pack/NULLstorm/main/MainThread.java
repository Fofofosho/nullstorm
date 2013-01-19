package pack.NULLstorm.main;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{

	static SurfaceHolder surfaceHolder;
	static MainGamePanel gamePanel;

	// flag to hold game state
	private boolean running;

	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel)
	{
		super();
		MainThread.surfaceHolder = surfaceHolder;
		MainThread.gamePanel = gamePanel;
	}

	public void run()
	{
		Canvas canvas;

		while (running)
		{
			canvas = null;
			FPSCounter.StartCounter(); // start counter
			canvas = MainThread.surfaceHolder.lockCanvas();
			synchronized (surfaceHolder)
			{
				gamePanel.update();
				try
				{
					Thread.sleep(0);
				} catch (InterruptedException ex)
				{
					gamePanel.paint(canvas);
				}
				gamePanel.paint(canvas);
			}
			if (canvas != null)
				surfaceHolder.unlockCanvasAndPost(canvas);
			FPSCounter.StopAndPost(); // calculate time
		}
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}
}
