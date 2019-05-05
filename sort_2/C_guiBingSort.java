package cn.me.sort_2;

//归并排序
public class C_guiBingSort {
	public static int compareCount = 0, changeCount = 0;

	public static void sort(int[] N, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int mi = (lo + hi) / 2;
		sort(N, lo, mi);
		sort(N, mi + 1, hi);
		merge(N, lo, mi, hi);
	}

	private static void merge(int[] N, int lo, int mi, int hi) {
		int a = lo, b = mi + 1, c = 0;
		int[] C = new int[hi - lo + 1];
		while (a <= mi && b <= hi) {
			if (N[a] < N[b]) {
				C[c++] = N[a++];
			} else {
				C[c++] = N[b++];
			}
			compareCount++;
			changeCount++;
		}
		for (int i = a; i <= mi; i++) {
			C[c++] = N[i];
			changeCount++;
		}
		for (int i = b; i <= hi; i++) {
			C[c++] = N[i];
			changeCount++;
		}
		for (int i = lo; i <= hi; i++) {
			N[i] = C[i - lo];
			changeCount++;
		}
	}

	public static String[] sort(int[] N) {
		sort(N, 0, N.length - 1);
		String c = new Object() {
		}.getClass().getEnclosingClass().getSimpleName();
		String[] s = new String[] { c, String.valueOf(compareCount), changeCount + "" };
		return s;
	}
}
