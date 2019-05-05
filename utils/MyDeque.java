package cn.me.utils;

import java.util.LinkedList;

import org.apache.log4j.Logger;

public class MyDeque<T> extends LinkedList<T> {
	public static Logger log = Logger.getLogger("log1");

	public void myadd(T i, T len) {
		double f = (Double) getFirst();
		double len1 = (Double) len;
		len1 = ((double) Math.round((len1) * 100)) / 100;
		double i1 = (Double) i;
		i1 = ((double) Math.round((i1) * 100)) / 100;
		if (f - len1 < 0.001) {
			log.debug("first=" + f + "\tlen1=" + len1 + "\tremovefirst");
			removeFirst();
		}
		while (size() > 0) {
			double l = (Double) getLast();
			if (l > i1) {
				log.debug("last=" + l + "\tnew=" + i1 + "\tremovelast1");
				removeLast();
			} else {
				break;
			}
		}
		addLast(i);
	}

	public void myadd(T i) {
		double i1 = (Double) i;
		i1 = ((double) Math.round((i1) * 100)) / 100;
		while (size() > 0) {
			double l = (Double) getLast();
			if (l > i1) {
				log.debug("last=" + l + "\tnew=" + i1 + "\tremovelast1");
				removeLast();
			} else {
				break;
			}
		}
		addLast(i);
	}

	public void mydelete(T i) {
		double f = (Double) getFirst();
		double i1 = (Double) i;
		i1 = ((double) Math.round((i1) * 100)) / 100;
		if (f - i1 < 0.001) {
			removeLast();
		}
	}
}
