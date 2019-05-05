package cn.me.other;

import java.util.Random;

public class ItemALl implements Runnable {
	private Item p;
	private Random r = new Random();

	public void pro() {
		synchronized (p) {
			int size = p.l.size();
			if (size >= 5) {
				System.out.println("full=============");
				try {
					p.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				p.l.add(size);
				p.notifyAll();
			}
		}
	}

	public void us() {
		synchronized (p) {
			int size = p.l.size();
			if (size <= 0) {
				System.out.println("00000=============");
				try {
					p.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println(p.l.remove(0));
				p.notifyAll();
			}
		}
	}

	public void run() {
		while (true) {
			int i = r.nextInt(2);
			if (i > 0) {
				pro();
			} else {
				us();
			}
			try {
				Thread.sleep(111);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Item item = new Item();
		for (int i = 0; i < 3; i++) {
			item.l.add(i);
		}
		for (int i = 0; i < 4; i++) {
			new Thread(new ItemALl(item)).start();
		}
	}

	public ItemALl(Item p) {
		super();
		this.p = p;
	}

}
