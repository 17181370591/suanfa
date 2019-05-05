package cn.me.dp;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import cn.me.utils.MyDeque;
import cn.me.utils.SortUtils;

//单调队列加dp解最大子序和
//描述：一个长度为n的整数序列，从中找出一段不超过m的连续子序列，使得整个序列的和最大。
public class LargestMNumbers {
	Deque<Integer> deque = new LinkedList<Integer>();
	private int[] ii;
	private int len = 40;
	int[] sum;

	@Test
	public void findLargestMNumbers() {
		MyDeque<Double> my = new MyDeque<Double>();
		int m = 3;
		int max = Math.max(sum[0], sum[0] + sum[1]);
		System.out.println(Arrays.toString(sum));
		for (int i = 0; i < len - 1; i++) {
			if (i >= m) {
				my.myadd((double) sum[i], (double) sum[i - m]);
			} else {
				my.myadd((double) sum[i]);
			}
			max = (int) Math.max(max, sum[i + 1] - my.getFirst());
			System.out.println(i + "\t" + my + "\tmax:" + max);
		}

	}

	public int[] getArray() {
		int x = 9;
		int[] a = SortUtils.getArray(len, x);
		for (int i = 0; i < len; i++) {
			a[i] = a[i] - x / 2;
		}
		return a;
	}

	@Before
	public void init() {
		ii = getArray();
		len = ii.length;
		sum = new int[ii.length];
		sum[0] = ii[0];
		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + ii[i];
		}
		System.out.println("ii=\t" + Arrays.toString(ii));
		System.out.println("sum=\t" + Arrays.toString(sum));
	}
}
