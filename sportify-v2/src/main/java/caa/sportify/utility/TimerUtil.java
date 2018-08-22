package caa.sportify.utility;

public class TimerUtil {

	private double startTime;
	private double endTime;

	public TimerUtil() {

	}

	public static TimerUtil getIntance() {
		return new TimerUtil();
	}

	public void startTimer() {
		startTime = System.nanoTime();
	}

	public void endTimer() {
		endTime = System.nanoTime();
	}

	public double getDuration() {
		return (endTime - startTime) / 1000000000;
	}

	public void printDuration() {
		System.out.println("Duration: " + getDuration() + " seconds.");
	}

}