package cn.me.other;

public class Singleton {
	private static Singleton instance;
	public int j = 0;

	private Singleton() {
	}

	public synchronized static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
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