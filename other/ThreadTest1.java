package cn.me.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class ThreadTest1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(3);
		List<Future<?>> taskResults = new ArrayList();
		for (int i = 0; i < 11; i++) {
			final int j = i;
			Future<?> taskResult = pool.submit(new Callable<Integer>() {
				public Integer call() throws Exception {
					String name = Thread.currentThread().getName();
					System.out.println(j + ":start\t" + name);
					try {
						Thread.sleep(1111);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(j + ":end\t" + name);
					return j;
				}
			});
			taskResults.add(taskResult);
		}
		for (Future<?> taskResult : taskResults) {
			System.out.println(taskResult.get());
		}
		System.out.println("over");
		pool.shutdown();
	}

	@Test
	public void tt() {

		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
		Random r = new Random();
		for (int i = 0; i < 22; i++) {
			System.out.print(r.nextInt(1) + "\t");
		}

	}
}
