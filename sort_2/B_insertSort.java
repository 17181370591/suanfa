package cn.me.sort_2;

import cn.me.utils.SortUtils;

//插入排序
public class B_insertSort {
	public static int compareCount = 0, changeCount = 0;

	public static String[] sort(int[] N, int lo, int hi) {
		for (int i = lo + 1; i < hi - lo + 1; i++) {
			int j = i;
			while (j > lo && N[j] < N[j - 1]) {
				compareCount++;
				SortUtils.change(N, j, j - 1);
				changeCount++;
				j--;
			}
			compareCount++;
		}
		String c = new Object() {
		}.getClass().getEnclosingClass().getSimpleName();
		String[] s = new String[] { c, String.valueOf(compareCount), changeCount + "" };
		return s;
	}

	public static String[] sort(int[] N) {
		return sort(N, 0, N.length - 1);
	}
}
