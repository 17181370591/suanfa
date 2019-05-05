package cn.me.sort_2;

import cn.me.utils.SortUtils;

//堆排序
public class E_heapSort {
	public static int compareCount = 0, changeCount = 0;

	public static String[] sort(int[] N) {
		int hi = N.length;
		int[] NN = new int[hi + 1];
		for (int i = 0; i < hi; i++) {
			NN[i + 1] = N[i];
			changeCount++;
		}

		for (int i = hi / 2; i > 0; i--) {
			sink(NN, hi, i);
		}

		while (hi > 1) {
			SortUtils.change(NN, 1, hi--);
			changeCount++;
			sink(NN, hi, 1);
		}

		for (int i = 0; i < N.length; i++) {
			N[i] = NN[i + 1];
			changeCount++;
		}

		String c = new Object() {
		}.getClass().getEnclosingClass().getSimpleName();
		String[] s = new String[] { c, String.valueOf(compareCount), changeCount + "" };
		return s;
	}

	private static void sink(int[] N, int hi, int i) {
		while (i <= hi / 2) {
			int j = i * 2;
			if (j < hi && N[j + 1] > N[j]) {
				compareCount++;
				j = j + 1;
			}
			if (N[i] < N[j]) {
				compareCount++;
				SortUtils.change(N, i, j);
				changeCount++;
			}
			i = j;
		}
	}

}
