package cn.me.models;

public class ColorFactory extends AbstractFactory {
	public Color getColor(String s) {
		if ("r".equalsIgnoreCase(s)) {
			return new Red();
		}
		if ("g".equalsIgnoreCase(s)) {
			return new Green();
		}
		return null;
	}

	@Override
	public Shape getShape(String s) {
		return null;
	}
}
