package top.lilixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Project: kafka
 * @Author: lilixin
 * @Date: 2016��8��16��
 * @Copyright: 2016 www.lilixin.top Inc. All rights reserved.
 */
public class TestConsumer implements TopConsumer {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void dealMsg(String msg) {
		logger.info("***********************");
		logger.info("Ҫ���ѵ���Ϣ={}", msg);
		logger.info("***********************");
	}

}
