package cn.me.other;

import org.junit.Test;

public class BeautifulNumber {

	private int b = 18;
	private int a = 11;
	private long m = (long) ((Math.pow(a, b) - 1) / (a - 1));

	@Test
	public void test2222() {
		for (int i = 64; i > 1; i--) {
			long t = getRadis(i);
			if (t > 0) {
				int k = 0;
				long m1 = m;
				while (m1 > 0) {
					k += 1;
					m1 = m1 / 10;

				}
				System.out.println(k + "\t" + m + "\t" + t + "\t" + i);
				return;
			}
		}
	}

	private long getRadis(int i) {
		long min = 2;
		long max = m;
		while (min <= max) {
			long mid = min + (max - min) / 2;
			long n = (long) ((Math.pow(mid, i) - 1) / (mid - 1));
			if (n == m) {
				return mid;
			}
			if (n < m) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		return -1;
	}

}
