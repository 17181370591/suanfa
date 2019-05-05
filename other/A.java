package cn.me.other;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class A implements Runnable {
	public static int j = 100;

	public static void main(String[] args) {
		Runnable a = new A();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(a);
			t.start();

		}
		System.out.println("A.j=" + A.j);
	}

	Lock l = new ReentrantLock();

	public void run() {

		while (true) {
			try {
				l.lock();
				if (j > 0) {
					Thread.sleep(1);
					System.out.println(Thread.currentThread().getName() + "\t" + (--j));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				l.unlock();
			}
		}
	}

}
