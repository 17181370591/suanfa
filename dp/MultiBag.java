package cn.me.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.junit.Test;

import cn.me.utils.MyDeque2;

/*
 * 多重背包方法1 multiToDequeBag，使用单调队列，最重要的是第一点：
 * 1、比如b+v需要比较(w0+w,w1)，b+2v需要比较(w0+2*w,w1+w,w2)，5个数都不一样，不能用单调队列，
 * 所以把每个位置的值都减去a*w1即可，这样b+v就比较(w0,w1-w)，b+2v比较(w0,w1-w,w2-2*w),
 * 这一点极其重要，要时刻按照这个思路写代码，不然经常忘记写到哪和要干什么，多写注释也许可以解决
 * 2、不能原地修改，从左往右和从右往左都不可以
 * 3、double处理数据经常出现1+1=1.999999的情况
 * 4、用deque实现单调队列时，插入数据逻辑写错了，把double l = (Double) getLast()写到while外面了
 *  		while (size() > 0) {
			double l = (Double) getLast();
			if (l < i1) {
				System.out.println("last=" + l + "\tnew=" + i1 + "\tremove last1");
				removeLast();
			} else {
				break;
			}
		}
 * 多重背包方法2 multiToBinaryBag，把M[i]拆成2进制再把多重背包转01背包
 * 多重背包方法3 multiToBinaryAndCompleteBag，如果M[i]足够(C[i]*M[i]>=V)，
 * 用completeBag的方法即从左往右遍历，否则用multiToBinaryBag的方法
 * */

public class MultiBag {
	private Deque<Integer> deque = new LinkedList<Integer>();
	private int[] C = { 21, 13, 26, 7, 16 };
	private double[] W = { 10.5, 6, 12, 3.6, 7.4 };
	private int V = 100;
	private int N = C.length;
	private int[] M = { 4, 5, 2, 6, 4 };
	private double[][] f = new double[N + 1][V + 1];
	private double[] f1 = new double[V + 1];

	@Test
	public void multiToBinaryBag() {
		for (int i = 1; i <= N; i++) {
			int ii = i - 1;
			int c = C[ii];
			double w = W[ii];
			int a = Math.min(V / c, M[ii]);
			int n = 1;
			while (n <= a) {
				// f[i][j]=max{ f[i-1][j-k*v]+kw 其中0=< k <= min(M[k], j/v) }
				for (int x = V; x >= n * c; x--) {
					f1[x] = fix(Math.max(f1[x], f1[x - n * c] + n * w), 1);
				}
				a = a - n;
				n = n * 2;
			}
			for (int x = V; x >= a * c; x--) {
				f1[x] = fix(Math.max(f1[x], f1[x - a * c] + a * w), 1);
			}
			System.out.println(Arrays.toString(f1));
		}
	}

	@Test
	public void multiToBinaryAndCompleteBag() {
		for (int i = 1; i <= N; i++) {
			int ii = i - 1;
			int c = C[ii];
			double w = W[ii];
			int a = Math.min(V / c, M[ii]);
			if (V / c <= M[ii]) {
				for (int x = c; x <= V; x++) {
					f1[x] = fix(Math.max(f1[x], f1[x - c] + w), 1);
				}
			} else {
				int n = 1;
				while (n <= a) {
					// f[i][j]=max{ f[i-1][j-k*v]+kw 其中0=< k <= min(M[k], j/v) }
					for (int x = V; x >= n * c; x--) {
						f1[x] = fix(Math.max(f1[x], f1[x - n * c] + n * w), 1);
					}
					a = a - n;
					n = n * 2;
				}
				for (int x = V; x >= a * c; x--) {
					f1[x] = fix(Math.max(f1[x], f1[x - a * c] + a * w), 1);
				}
			}
			System.out.println(Arrays.toString(f1));
		}
	}

	// 另 a=j/v b=j%v k=a-k
	/**
	 * test2的代码是错误的，不能原地修改。因为从左到右会修改值， 从右到左队列会丢失应有数据 f[i][j]=max{
	 * f[i-1][j-k*v]+kw 其中0=< k <= min(M[k], j/v) } f[i][j]=max{
	 * (f[i-1][b+k*v]-kw) +aw 其中a-min(M[k], a) =< k <= a
	 */
	@Test
	public void multiToDequeBag() {
		for (int i = 1; i <= N; i++) {
			int v = C[i - 1];
			double w = W[i - 1];
			ArrayList<MyDeque2<Double>> mys = new ArrayList<MyDeque2<Double>>();
			for (int j = 0; j < v; j++) {
				f[i][j] = fix((double) f[i - 1][j], 2);
				MyDeque2<Double> my = new MyDeque2<Double>();
				my.myadd(f[i][j]);
				mys.add(my);
			}
			System.out.println("mys=" + mys);
			for (int j = v; j <= V; j++) {
				int a = j / v;
				int b = j % v;
				int lenq = M[i - 1] + 1;
				int k = a - lenq;
				MyDeque2<Double> myDeque = mys.get(b);
				// f[i][j]=max{(f[i-1][b+k*v]-kw) +aw 其中a-min(M[k], a) =< k <= a
				// 队列存放最多lenq个 f[i-1][b+k*v]-kw
				double second = fix(f[i - 1][j] - a * w, 2);
				double first = 0;
				if (k >= 0) {
					// 如果列第一个值等于 f[i-1][b+k*v]-kw ( k = a - lenq)，需要删除
					first = (double) (f[i - 1][b + k * v] - k * w);
					myDeque.myadd(second, fix(first, 2));
				} else {
					myDeque.myadd(second);
				}
				f[i][j] = fix(myDeque.getFirst() + a * w, 2);
				String s = "i=" + i + "\tj=" + j + "\tv=" + C[i - 1] + "\tm=" + M[i - 1];
				s = s + "\t" + myDeque + "\tmax=" + f[i][j] + "\tfirst=" + first;
				System.out.println(s);
			}
		}
		for (int i = 0; i <= N; i++) {
			System.out.println(Arrays.toString(f[i]));
		}
	}

	public double fix(double i, int j) {
		int x = (int) Math.pow(10, j);
		return ((double) Math.round((i) * 100)) / 100;
	}
}
