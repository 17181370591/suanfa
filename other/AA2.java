package cn.me.other;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AA2 implements Runnable {
	private List a = new ArrayList();
	private List b = new ArrayList();
	public static boolean bo;

	public static void main(String[] args) throws Exception {
		AA2 a2 = new AA2();
		for (int i = 0; i < 5; i++) {
			bo = !bo;
			new Thread(a2).start();
		}

	}

	@Test
	public void t1() {
		AA2 a2 = new AA2();
		// bo = true;
		new Thread(a2).start();
	}

	public void run() {
		if (bo) {
			synchronized (a) {
				System.out.println(Thread.currentThread().getName() + "lock a");
				try {
					Thread.sleep(44);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (b) {
					System.out.println("lock b");
					System.out.println(bo);
				}
				System.out.println("unlock b");
			}
		} else {
			synchronized (b) {
				System.out.println(Thread.currentThread().getName() + "lock b");
				try {
					Thread.sleep(44);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (a) {
					System.out.println("lock a");
					System.out.println(bo);
				}
				System.out.println("unlock a");
			}
		}
	}

}
