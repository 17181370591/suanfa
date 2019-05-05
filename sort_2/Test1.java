package cn.me.sort_2;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import cn.me.utils.SortUtils;

public class Test1 {
	private int len = 100000;

	@Test
	public void t1() throws Exception {
		Class cls = this.getClass();
		String[] fs = SortUtils.getJavaFiles(cls);
		int[] N = SortUtils.getArray(len);
		// int[] N = SortUtils.getArray(len, 3);
		for (int i = 0; i < fs.length - 1; i++) {
			int[] N1 = Arrays.copyOfRange(N, 0, len);
			// System.out.println(Arrays.toString(N1));
			String name = fs[i];
			testOneClass(name, N1);
		}
	}

	public void testOneClass(String name, int[] N) throws Exception {
		Class<?> cls = Class.forName(name);
		Method method = cls.getMethod("sort", int[].class);
		String[] s = (String[]) method.invoke(cls.newInstance(), N);
		// System.out.println(Arrays.toString(N));
		boolean r = SortUtils.isSorted(N);
		System.out.print(s[0] + "排序" + (r == true ? "成功" : "失败"));
		System.out.println("\t比较次数:" + s[1] + ",交换次数:" + s[2]);
	}
}
