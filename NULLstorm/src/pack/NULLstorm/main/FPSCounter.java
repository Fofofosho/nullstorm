package pack.NULLstorm.main;

final public class FPSCounter
{
	private static int startTime;
	private static int endTime;
	private static int frameTimes = 0;
	private static short frames = 0;
	private static int maxFrames = 0;

	public final static void StartCounter()
	{
		startTime = (int) System.currentTimeMillis();
	}

	public final static void StopAndPost()
	{
		endTime = (int) System.currentTimeMillis();
		frameTimes += endTime - startTime;
		frames++;

		if (frameTimes >= 1000)
		{
			maxFrames = frames;
			if (maxFrames > 60)
				maxFrames = 60;
			frames = 0;
			frameTimes = 0;
		}
	}

	public final static String getFPS()
	{
		return maxFrames + "";
	}
}