package cn.me.models;

public class Circle implements Shape {

	public void draw() {
		System.out.println("i am a circle");

	}

	@Override
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clone;
	}

}
