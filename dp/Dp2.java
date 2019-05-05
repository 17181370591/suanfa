package cn.me.dp;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/*本打算测试动态规划解决01背包，发现创建的数组必须是连续的数，
 * 即所有物品的cost的所有和只要小于背包的体积就全部出现在数组里(所以最好所有数都是整数)，
 * 我以为不需要这个条件，不存在的数向下查找即可。测试发现背包体积大于最大物品cost*2时
 * 容易出现bug，原因是比如体积a和b背包结果不同，但是b向下查找找到a，就会出现bug。
 * 这个代码写的很繁琐，可以大量优化
 * 简单的说就是非整数全部转成整数在用动态规划 */
public class Dp2 {
	private double V;
	private int N = 11;
	private double[] vs = new double[N + 1];
	private double[] ws = new double[N];
	static double small = -100f;
	static Dp2 d = new Dp2();

	@Before
	public void init() {
		d.V = 22;
		d.vs[0] = 0;
		d.ws[0] = 0;
		d.vs[d.N] = d.V;
		for (int i = 1; i < d.N; i++) {
			long r1 = Math.round(Math.random() * 9);
			d.vs[i] = (double) Math.round((i + 0.1 * r1) * 100) / 100;
			long r2 = Math.round(Math.random() * 5);
			d.ws[i] = (double) Math.round((i + 0.1 * r2) * 100) / 100;
		}
		System.out.println(d);
		System.out.println("===================================");
	}

	@Test
	public void t1() {
		dp(d.V, d.N, d.vs, d.ws);
		System.out.println("===================================");
		dp1(d.V, d.N, d.vs, d.ws);
	}

	public void dp(double V, int N, double[] vs, double[] ws) {
		double[][] f = new double[N][N + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < vs.length; j++) {
				if (i * j == 0) {
					f[i][j] = 0.0f;
				} else {
					double b = getF(d.vs, vs[j] - vs[i], f[i - 1]) + ws[i];
					// f[i - 1][j] f[i-1][j-vs[i]]
					double m = Math.max(f[i - 1][j], b);
					f[i][j] = (double) Math.round(m * 100) / 100;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(f[i]));
		}
	}

	public void dp1(double V, int N, double[] vs, double[] ws) {
		double[] f = new double[N + 1];
		for (int i = 0; i < N; i++) {
			for (int j = N; j >= 0; j--) {
				if (i * j == 0) {
					f[j] = 0;
				} else {
					double b = getF(d.vs, vs[j] - vs[i], f) + ws[i];
					// f[i - 1][j] f[i-1][j-vs[i]]
					double m = Math.max(f[j], b);
					f[j] = (double) Math.round(m * 100) / 100;
				}
			}
		}
		System.out.println(Arrays.toString(f));

	}

	@Test
	public void t2() {
		double[] vs = { 0.0, 1.4, 2.8, 3.6, 4.2, 5.7, 6.2, 7.6, 8.5, 9.0, 10.8, 22.0 };
		double[] ws = { 0.0, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7 };
		getF(vs, 4.2 - 2.8, ws);
	}

	private double getF(double[] vs, double f, double[] fs) {
		int s = -1;
		double r = small;
		if (f >= 0) {
			for (int i = 0; i < vs.length - 1; i++) {
				if (f >= vs[i] && f < vs[i + 1]) {
					s = i;
					break;
				}
			}
		}
		if (s >= 0) {
			r = fs[s];
		}
		// System.out.println("f=" + f + "\t第几个=" + s + "\t\tr=" + r);
		return r;
	}

	public double getV() {
		return V;
	}

	public void setV(double v) {
		V = v;
	}

	public double[] getVs() {
		return vs;
	}

	public void setVs(double[] vs) {
		this.vs = vs;
	}

	public double[] getWs() {
		return ws;
	}

	public void setWs(double[] ws) {
		this.ws = ws;
	}

	@Override
	public String toString() {
		return "Dp2 [V=" + V + ", N=" + N + ", \n" + Arrays.toString(vs) + "<---vs,\n" + Arrays.toString(ws) + "<--ws]";
	}

}

/*
 * Dp2 [V=22.0, N=11, [0.0, 1.3, 2.7, 3.7, 4.3, 5.8, 6.4, 7.3, 8.0, 9.8, 10.1,
 * 22.0]<---vs, [0.0, 1.1, 2.5, 3.3, 4.0, 5.5, 6.4, 7.3, 8.5, 9.2, 10.4]<--ws]
 * =================================== [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
 * 0.0, 0.0, 0.0, 0.0] [0.0, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1,
 * 1.1] [0.0, 1.1, 2.5, 2.5, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6, 3.6] [0.0, 1.1,
 * 2.5, 3.3, 3.6, 4.4, 5.8, 5.8, 6.9, 6.9, 6.9, 6.9] [0.0, 1.1, 2.5, 3.3, 4.0,
 * 5.1, 5.8, 6.5, 7.3, 7.6, 8.4, 10.9] [0.0, 1.1, 2.5, 3.3, 4.0, 5.5, 5.8, 6.6,
 * 7.3, 8.8, 9.5, 13.9] [0.0, 1.1, 2.5, 3.3, 4.0, 5.5, 6.4, 6.6, 7.5, 8.9, 9.5,
 * 15.9] [0.0, 1.1, 2.5, 3.3, 4.0, 5.5, 6.4, 7.3, 7.5, 8.9, 9.8, 16.8] [0.0,
 * 1.1, 2.5, 3.3, 4.0, 5.5, 6.4, 7.3, 8.5, 9.6, 9.8, 18.3] [0.0, 1.1, 2.5, 3.3,
 * 4.0, 5.5, 6.4, 7.3, 8.5, 9.6, 9.8, 19.0] [0.0, 1.1, 2.5, 3.3, 4.0, 5.5, 6.4,
 * 7.3, 8.5, 9.6, 10.4, 20.2] =================================== [0.0, 1.1,
 * 2.5, 3.3, 4.0, 5.5, 6.4, 7.3, 8.5, 9.6, 10.4, 20.2]
 */
