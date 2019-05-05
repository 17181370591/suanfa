package cn.me.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

//找0到x之间有相同数字的数的个数,6000->6375没有好办法
public class Dp8 {
	private int x = 6375;
	private int count;

	@Test
	public void test2() {
		// 1=<a<=9
		// a0->a9 10*a->10*(a+1)-1 10-9
		// a00->a99 100*a->100*(a+1)-1 10*10-9*8
		// a000->a999 10int count = 0; 10*10*10-9*8*7
		// 0-999 1000-5999 6000-6375
		int x1 = x;
		int len = 1;
		List<Integer> list = new ArrayList();
		while (x1 >= 10) {
			list.add(x1 % 10);
			x1 = x1 / 10;
			len += 1;
		}
		list.add(x1);
		System.out.println(list + "\t" + x1 + "\t" + len);
		for (int i = 2; i < len; i++) {
			System.out.println(count);
			count += getCount(i, 1) * 9;
		}
		System.out.println(count + "--");
		count += getCount(len, 1) * (x1 - 1); // 2909 2741 2873

		for (int i = len - 1; i >= 2; i--) {
			System.out.println(count);
			count += getCount(i, len - i + 1) * list.get(i - 1);
		}
		System.out.println(count);
	}

	@Test
	public void test1() {
		for (int i = 6300; i <= x; i++) {
			String s = i + "";
			char[] ca = s.toCharArray();
			Map m = new HashMap();
			for (int j = 0; j < ca.length; j++) {
				if (m.get(ca[j]) == null) {
					m.put(ca[j], 1);
				} else {
					count += 1;
					break;
				}
			}
		}
		System.out.println(count);
	}

	public int getCount(int x, int z) {
		x = x - 1;
		int y = 1;
		int from = 10 - z;
		for (int i = 0; i < x; i++) {
			y = y * (from - i);
		}
		int r = (int) (Math.pow(10, x) - y);
		System.out.println("r=" + r);
		return r;
	}

	@Test // 1-0 2-1 3-28
	public void test3() {
		System.out.println(getCount(3, 0));
	}
}