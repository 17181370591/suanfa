package cn.me.other;

public class ItemProduce implements Runnable {
	private Item p;

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

	public void run() {
		while (true) {
			pro();
			try {
				Thread.sleep(111);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public ItemProduce(Item p) {
		super();
		this.p = p;
	}

}
