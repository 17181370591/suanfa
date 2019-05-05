package cn.me.other;

import java.math.BigInteger;

import org.junit.Test;

public class A2 implements Runnable {
	public static int i;

	public void a() throws InterruptedException {
		if (i > 500) {
			return;
		}
		Thread.sleep(13);
		System.out.println(Thread.currentThread().getName() + ":" + i);
		i++;
		b();
	}

	public synchronized void b() throws InterruptedException {
		Thread.sleep(33);
		a();
	}

	public static void main(String[] args) {
		A2 a = new A2();
		for (int i = 0; i < 10; i++) {
			new Thread(new A2()).start();
		}
	}

	public void run() {
		try {
			a();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void run1() {
		String s = "777777777777777777777777777";
		BigInteger a = new BigInteger(s);
		BigInteger b = new BigInteger("27");
		System.out.println(a.divide(b));
	}

}
