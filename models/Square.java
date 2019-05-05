package cn.me.models;

public class Square implements Shape {

	public void draw() {
		System.out.println("i am a Square");

	}

	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}
