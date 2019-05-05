package cn.me.sort_2;

import cn.me.utils.SortUtils;

//选择排序
public class A_chooseSort {
	public static int compareCount = 0, changeCount = 0;

	public static String[] sort(int[] N, int lo, int hi) {
		for (int i = lo; i < hi - lo + 1; i++) {
			int minIndex = i, min = N[minIndex];
			for (int j = i + 1; j < hi - lo + 1; j++) {
				compareCount++;
				if (min > N[j]) {
					minIndex = j;
					min = N[minIndex];
				}
			}
			SortUtils.change(N, i, minIndex);
			changeCount++;
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
