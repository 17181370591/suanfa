package cn.me.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.me.utils.Fix;

/* 计算01/完全背包的第K大的值,思路是res的每个值是个list,保存排好序的最大的k个值(不够k个则空缺)。
 * res[j] 的值 等于 res[j]和 res[j-v]+w 这K*2个值(可能不够)的最大的K个值按顺序组成的list。 
*/
public class KthBiggestW {
	private int[] C = { 21, 13, 26, 7, 16 };
	private double[] W = { 10.5, 6, 12, 3.6, 7.4 };
	private int N = C.length;
	private int V = 52;
	private int K = 5;
	private int len = C.length;
	private List<Double>[] res = new ArrayList[V + 1];

	@Before
	public void init() {
		for (int i = 0; i <= V; i++) {
			List<Double> l = new ArrayList();
			l.add(0.0);
			res[i] = l;
		}
	}

	@Test
	public void zeroOneBag() {
		for (int i = 0; i < N; i++) {
			int v = C[i];
			for (int j = V; j >= v; j--) {
				res[j] = getMaxK(res[j], res[j - v], W[i]);
			}
			System.out.println(Arrays.toString(res));
		}
	}

	@Test
	public void completeBag() {
		for (int i = 0; i < N; i++) {
			int v = C[i];
			for (int j = v; j <= V; j++) {
				res[j] = getMaxK(res[j], res[j - v], W[i]);
			}
			System.out.println(Arrays.toString(res));
		}
	}

	// 把两个list的最大的k个值拼成1个有序的list
	public List<Double> getMaxK(List<Double> a, List<Double> b, double w) {
		if (b == null || b.size() == 0)
			return a;
		List<Double> c = new ArrayList<Double>();
		for (int i = 0; i < b.size(); i++) {
			c.add(b.get(i) + w);
		}
		if (a == null || a.size() == 0)
			return c;
		List<Double> r = new ArrayList<Double>();
		int k1 = 0, k2 = 0;
		while (k1 < a.size() && k2 < c.size()) {
			if (a.get(k1) >= c.get(k2)) {
				r.add(Fix.fix(a.get(k1++)));
			} else {
				r.add(Fix.fix(c.get(k2++)));
			}
			if (r.size() == K)
				return r;
		}
		while (k1 < a.size()) {
			r.add(Fix.fix(a.get(k1++)));
			if (r.size() == K)
				return r;
		}
		while (k2 < c.size()) {
			r.add(Fix.fix(c.get(k2++)));
			if (r.size() == K)
				return r;
		}
		return r;
	}

}
