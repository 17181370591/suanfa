package cn.me.models;

public abstract class AbstractFactory {
	public abstract Color getColor(String s);

	public abstract Shape getShape(String s);
}
