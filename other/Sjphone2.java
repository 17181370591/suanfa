package cn.me.other;

//消费类
public class Sjphone2 implements Runnable {
	public Shouji con;

	public Sjphone2(Shouji con) {// 构造函数
		this.con = con;
	}

	public void run() {
		while (true) {// 一直运行
			synchronized (con) {// 手机锁
				if (con.currentNum > 0) {// 当有手机的时候
					con.notify();// 购买手机 并释放锁
					con.currentNum--;
					System.out.println(" 消费者正在消费小米手机卖出1部, 当前产品数：" + con.currentNum + "部");
				} else if (con.currentNum == 0) {// 若没有手机了
					System.out.println("手机已经买完了，消费者停止消费，正在等待生产...");
					try {
						con.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // 暂停消费 并释放锁

				} // else if
			} // syn

			try {
				Thread.sleep(140);// 调节消费者频率，过快容易撑死~~
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // try
		} // while
	}// run
}