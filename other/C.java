package cn.me.other;

import java.util.concurrent.Callable;

public class C implements Callable {

	public Object call() throws Exception {
		int i = 0;
		for (; i < 10; i++) {
			Thread.sleep(22);
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		return i;
	}

}
