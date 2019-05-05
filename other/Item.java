package cn.me.other;

import java.util.ArrayList;
import java.util.List;

public class Item {
	public static List l = new ArrayList();

	public static void main(String[] args) {
		Item item = new Item();
		for (int i = 0; i < 11; i++) {
			item.l.add(i);
		}
		for (int i = 0; i < 2; i++) {
			new Thread(new ItemProduce(item)).start();
		}
		for (int i = 0; i < 3; i++) {
			new Thread(new ItemUse(item)).start();
		}
	}
}
