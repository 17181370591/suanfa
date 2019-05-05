package cn.me.utils;

import java.io.File;

public class SortUtils {
	public static void change(int[] N, int a, int b) {
		int c = N[a];
		N[a] = N[b];
		N[b] = c;
	}

	public static boolean isSorted(int[] N) {
		for (int i = 1; i < N.length; i++) {
			if (N[i] < N[i - 1]) {
				return false;
			}
		}
		return true;
	}

	// 获得一个乱序无相同值的int[]，思路是int[]随机取一个和尾部交换，忽略尾部
	public static int[] getArray(int len) {
		int[] N = new int[len];
		for (int i = 0; i < len; i++) {
			N[i] = i;
		}
		int len1 = len;
		while (len1 > 1) {
			int m = (int) (Math.random() * len1);
			SortUtils.change(N, len1 - 1, m);
			len1--;
		}
		return N;
	}

	// 获得一个乱序有相同值的int[]，思路是int[]随机取一个和尾部交换，忽略尾部
	public static int[] getArray(int len, int range) {
		int[] N = new int[len];
		for (int i = 0; i < len; i++) {
			N[i] = i % range;
		}
		int len1 = len;
		while (len1 > 1) {
			int m = (int) (Math.random() * len1);
			SortUtils.change(N, len1 - 1, m);
			len1--;
		}
		return N;
	}

	// 通过类名获得.java文件所在的路径下的所有.java文件
	public static String[] getJavaFiles(Class cls) {
		String path = cls.getResource("").getPath();
		// path是class文件的路径，即target所在的李静
		path = path.replace("target/classes", "src/main/java");
		Package package1 = cls.getPackage();
		String[] fs = new File(path).list();
		String packageName = cls.getPackage().getName();
		for (int i = 0; i < fs.length; i++) {
			// fs[i]是如A_chooseSort.java，需要改成如cn.me.sort_2.A_chooseSort
			fs[i] = packageName + "." + fs[i].replace(".java", "");
		}
		return fs;
	}
}
