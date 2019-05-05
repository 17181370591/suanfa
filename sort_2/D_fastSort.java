package cn.me.sort_2;

import cn.me.utils.SortUtils;

//快速排序
//注意while (a <= hi && N[a] < N[lo])判断时边界判断一定要写前面
public class D_fastSort {
	public static int compareCount = 0, changeCount = 0;

	public static void sort(int[] N, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int mi = sortOne(N, lo, hi);
		sort(N, lo, mi - 1);
		sort(N, mi + 1, hi);
	}

	private static int sortOne(int[] N, int lo, int hi) {
		int a = lo + 1, b = hi;
		while (true) {
			while (a <= hi && N[a] < N[lo]) {
				a++;
				compareCount++;
			}
			while (b >= lo && N[b] > N[lo]) {
				b--;
				compareCount++;
			}
			if (a < b) {
				SortUtils.change(N, a++, b--);
				changeCount++;
			} else {
				break;
			}
		}
		SortUtils.change(N, lo, b);
		changeCount++;
		return b;
	}

	public static String[] sort(int[] N) {
		sort(N, 0, N.length - 1);
		String c = new Object() {
		}.getClass().getEnclosingClass().getSimpleName();
		String[] s = new String[] { c, String.valueOf(compareCount), changeCount + "" };
		return s;
	}
}
