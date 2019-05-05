package cn.me.other;

public class ItemUse implements Runnable {
	private Item p;

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
			us();
			try {
				Thread.sleep(111);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public ItemUse(Item p) {
		super();
		this.p = p;
	}

}
