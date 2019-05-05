package cn.me.dp;

import java.util.Arrays;

import org.junit.Test;

/*
 * 二维背包,f(i,x,y)表示用前i个求装v1=x,v2=y的背包的最大价值，
 * 所以01背包满足: f(i,x,y)=max{ f(i-1,x,y) , f(i-1,x-c1,y-c2)+w },
 * 千万注意2维背包的遍历顺序有个坑：
 * 我的顺序是	for (int i = 1; i <= N; i++) 
					int ii = i - 1;
					for (int x = V1; x >= C1[ii]; x--) 
						for (int y = V2; y >= C2[ii]; y--) 
先球再维度，这里没有出现bug是因为球的先后顺序不重要不影响结果，如果要考虑顺序，
一定要先维度，最后再遍历球，维度全部从1开始，进行越界判断(python里-1会倒着取不报错)
 * */

public class TwoDBag {
	/*
	 * private int[] C1 = { 7, 13, 16, 21, 26 }; private int[] C2 = { 11, 3, 25,
	 * 23, 16 }; private double[] W = { 3.6, 6, 7.4, 10.5, 12 }; private int[] M
	 * = { 6, 5, 4, 4, 2 };
	 */
	private int[] C1 = { 16, 26, 7, 21, 13 };
	private int[] C2 = { 25, 16, 11, 23, 3 };
	private double[] W = { 7.4, 12, 3.6, 10.5, 6 };
	private int[] M = { 4, 2, 6, 4, 5 };
	private int V1 = 60;
	private int V2 = 50;
	private int N = C1.length;

	private double[][] f = new double[V1 + 1][V2 + 1];

	@Test
	public void zeroOne2dBag() {
		for (int i = 1; i <= N; i++) {
			int ii = i - 1;
			for (int x = V1; x >= C1[ii]; x--) {
				for (int y = V2; y >= C2[ii]; y--) {
					f[x][y] = Math.max(f[x][y], f[x - C1[ii]][y - C2[ii]] + W[ii]);
				}
				System.out.println("x=" + x + "时\t" + Arrays.toString(f[x]));
			}
			System.out.println("==============================================");
		}
	}

	@Test
	public void complete2dBag() {
		for (int i = 1; i <= N; i++) {
			int ii = i - 1;
			for (int x = C1[i - 1]; x <= V1; x++) {
				for (int y = C2[i - 1]; y <= V2; y++) {
					f[x][y] = Math.max(f[x][y], f[x - C1[ii]][y - C2[ii]] + W[ii]);
				}
				System.out.println("x=" + x + "时\t" + Arrays.toString(f[x]));
			}
			System.out.println("==============================================");
		}
	}

	@Test // 2维多重背包，未完成
	public void multi2dBag() {
		for (int i = 1; i <= N; i++) {
			int ii = i - 1;
			for (int x = V1; x >= C1[ii]; x--) {
				for (int y = V2; y >= C2[ii]; y--) {
					f[x][y] = Math.max(f[x][y], f[x - C1[ii]][y - C2[ii]] + W[ii]);
				}
				System.out.println("x=" + x + "时\t" + Arrays.toString(f[x]));
			}
			System.out.println("==============================================");
		}
	}
}
