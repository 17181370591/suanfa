package cn.me.other;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.junit.Test;

import cn.me.models.Circle;
import cn.me.models.Shape;

public class A1 {
	public int i = 0;

	@Test
	public void ttttt() {
		Runnable a = new A();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(a);
			t.start();

		}
		System.out.println("A.j=" + A.j);
		while (true)
			;
	}

	@Test
	public void ttttt1() throws Exception {
		int i;
		// FutureTask[] l = new FutureTask[10];
		List<FutureTask> li = new ArrayList();

		for (i = 0; i < 10; i++) {
			FutureTask ft = new FutureTask(new C());
			// l[i] = ft;
			li.add(ft);
			Thread t = new Thread(ft);
			t.setDaemon(true);
			t.start();
		}
		for (i = 0; i < 10; i++) {
			// System.out.println(l[i].get());
			System.out.println(li.get(i).get());
		}
		while (true)
			;
	}

	@Test
	public void tttt() {
		for (int i = 0; i < 20; i++) {
			final Singleton2 s1 = Singleton2.getInstance();
			new Thread(new Runnable() {
				public void run() {
					s1.add();
				}
			}).start();
		}
		while (true)
			;
	}

	@Test
	public void ttt() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Singleton.getInstance());
		}

	}

	@Test
	public void ttt1() throws Exception {
		final Circle c = new Circle();
		Shape c1 = (Shape) Proxy.newProxyInstance(c.getClass().getClassLoader(), c.getClass().getInterfaces(),
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if ("draw".equals(method.getName())) {
							System.out.println("+++");
							return method.invoke(c, args);
						}
						return null;
					}
				});
		c1.draw();
	}

}
