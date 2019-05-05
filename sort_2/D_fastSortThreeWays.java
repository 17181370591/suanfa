package cn.me.sort_2;

import cn.me.utils.SortUtils;

//3向切分排序
//注意while (a <= hi && N[a] < N[lo])判断时边界判断一定要写前面
public class D_fastSortThreeWays {
	public static int compareCount = 0, changeCount = 0;

	public static void sort(int[] N, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int[] mi = sortOne(N, lo, hi);
		sort(N, lo, mi[0] - 1);
		sort(N, mi[1] + 1, hi);
	}

	private static int[] sortOne(int[] N, int lo, int hi) {
		int a = lo + 1, b = hi, c = lo, x = N[lo];
		while (a <= b) {
			if (x < N[a]) {
				SortUtils.change(N, a, b--);
				compareCount++;
				changeCount++;
			} else if (x == N[a]) {
				a++;
				changeCount--;
			} else {
				SortUtils.change(N, a++, c++);

			}
			compareCount++;
			changeCount++;
		}
		return new int[] { c, b };
		// -3,-1,0(c),0,a/b,x,x
		// -3,-1,0(c),0(b),x(a),x,x
		// -3,-1,0(c),0,0(b),x(a),x
		// -3,-1,x,0(c),0(b),x(a),x
	}

	public static String[] sort(int[] N) {
		sort(N, 0, N.length - 1);
		String c = new Object() {
		}.getClass().getEnclosingClass().getSimpleName();
		String[] s = new String[] { c, String.valueOf(compareCount), changeCount + "" };
		return s;
	}
}
