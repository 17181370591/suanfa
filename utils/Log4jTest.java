package cn.me.utils;

import org.apache.log4j.Logger;

public class Log4jTest {

	static Logger logger = Logger.getLogger(Log4jTest.class);

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			logger.info("这是个debug级别的输出，会出现在控制台和一个log日志文件中");
		}

	}
}