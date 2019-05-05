package cn.me.models;

import org.junit.Test;

public class MultiFactory {
	public AbstractFactory getFactory(String r) {
		if ("s".equalsIgnoreCase(r)) {
			return new ShapeFactory();
		} else {
			return new ColorFactory();
		}
	};

	@Test
	public void cccc() {
		MultiFactory m = new MultiFactory();
		m.getFactory("xs").getColor("r").light();
		;
		m.getFactory("s").getShape("0").draw();
		;
	}
}
