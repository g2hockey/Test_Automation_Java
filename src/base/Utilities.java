package base;

public class Utilities {

	public static void sleep(long dur) {
		try {
			Thread.sleep(dur);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
