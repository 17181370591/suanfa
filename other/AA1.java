package cn.me.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class AA1 {
	public static void main(String[] args) {
		// 创建线程池对象 参数5，代表有5个线程的线程池
		ExecutorService service = Executors.newFixedThreadPool(3);
		// 创建Runnable线程任务对象
		TaskRunnable task = new TaskRunnable();
		// 从线程池中获取线程对象
		for (int i = 0; i < 5; i++) {
			service.submit(task);
		}
		// 关闭线程池
		service.shutdown();
	}

	@Test
	public void t2() {
		AA2 a2 = new AA2();
		a2.bo = false;
		Thread t = new Thread(a2);
		t.start();
	}
}

class TaskRunnable implements Runnable {
	public void run() {
		for (int i = 0; i < 6; i++) {
			try {
				Thread.sleep(22);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}

}