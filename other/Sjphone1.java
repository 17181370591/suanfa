package cn.me.other;
//生产手机类
public class Sjphone1 implements Runnable {
	public Shouji con;

	public Sjphone1(Shouji con) {// 构造函数
		this.con = con;
	}

	public void run() {
		while (true) {// 一直运行
			synchronized (con) {// 手机类锁
				if (con.currentNum < con.max) {// 若当前容器不满，则可以生产
					con.notify();// 生产完则通知并释放锁
					con.currentNum++;
					System.out.println(" 生产者正在生产小米手机1部, 当前产品数：" + con.currentNum + "部");
				} else if (con.currentNum == con.max) {// 若生产手机等于最大定义手机市场容量
					System.out.println("手机生产已经饱和，生产者停止生产，正在等待消费...");
					try {
						con.wait();// 暂停生产 并释放锁
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // if else if
			} // syn 执行完同步块
				// 释放锁，输出结果中连续出现两次生产者是因为：释放锁后，若没有等待线程，则还是先执行到哪个线程的同步块就执行它

			try {
				Thread.sleep(100);// 调节生产者频率，过快容易猝死~~
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // try
		} // while
	}
}