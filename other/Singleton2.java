package cn.me.other;

public class Singleton2 {
	private static Singleton2 instance = new Singleton2();
	public int j = 0;

	private Singleton2() {
	}

	public static Singleton2 getInstance() {
		return instance;
	}

	public void add() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\t" + (++j));
		}
	}
}