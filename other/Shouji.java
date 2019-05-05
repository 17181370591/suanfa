package cn.me.other;

public class Shouji {
	public int max; // 定义容器最大容量
	public int currentNum;// 定义容器当前容量

	public Shouji(int max) {
		this.max = max;
		currentNum = 0;
	}

	public static void main(String[] args) {
		Shouji container = new Shouji(5);// 定义最大定义手机市场容量，此处为5
		Sjphone1 sj1 = new Sjphone1(container);// 箱子中的手机数要同步，所以将手机对象引用作为形参传给生产者和消费者
		Sjphone2 sj2 = new Sjphone2(container);// 同上
		// 启动生产消费模式
		new Thread(sj1, "生产").start();
		new Thread(sj2, "消费").start();
	}
}