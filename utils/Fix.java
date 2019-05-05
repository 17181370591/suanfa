package cn.me.utils;

public class Fix {
	public static double fix(double i, int j) {
		int x = (int) Math.pow(10, j);
		return ((double) Math.round(i * x)) / x;
	}

	public static double fix(double i) {
		int x = (int) Math.pow(10, 2);
		return ((double) Math.round(i * x)) / x;
	}

}
