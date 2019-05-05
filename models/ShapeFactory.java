package cn.me.models;

import org.junit.Test;

public class ShapeFactory extends AbstractFactory {
	public Shape getShape(String s) {
		if ("0".equalsIgnoreCase(s)) {
			return new Circle();
		}
		if ("4".equalsIgnoreCase(s)) {
			return new Square();
		}
		return null;
	}

	@Override
	public Color getColor(String s) {
		return null;
	}

	@Test
	public void test1() {
		ShapeFactory f = new ShapeFactory();
		Shape s = f.getShape("0");
		s.draw();
		Shape s1 = (Shape) s.clone();
		System.out.println(s + "\t" + s1);
		s1.draw();
		f.getShape("4").draw();
	}

}
