/*
 * @��Ŀ����: kafka
 * @�ļ�����: TopProducer.java
 * @Date: 2016-8-16
 * @Copyright: 2016 www.lilixin.top Inc. All rights reserved.
 * ע�⣺�����ݽ������ڲ����ģ���ֹ��й�Լ�������������ҵĿ��
 */
package top.lilixin;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * @Project: kafka
 * @Author: lilixin
 * @Copyright: 2016 www.lilixin.top Inc. All rights reserved.
 */
public class TopProducer {

	private Logger log = LoggerFactory.getLogger(TopProducer.class);

	private String metadataBrokerList;

	private Producer<String, String> producer;

	public TopProducer(String metadataBrokerList) {
		super();
		if (StringUtils.isEmpty(metadataBrokerList)) {
			String message = "metadataBrokerList ������Ϊ��";
			throw new RuntimeException(message);
		}
		this.metadataBrokerList = metadataBrokerList;
		// ������������
		Properties props = new Properties();
		props.put("metadata.broker.list", metadataBrokerList);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");
		// props.put("producer.type", "async");
		props.put("queue.buffering.max.ms", "5000");
		props.put("queue.buffering.max.messages", "30000");
		props.put("queue.enqueue.timeout.ms", "-1");
		props.put("batch.num.messages", "1");
		//props.put("num.partitions", "3"); //这个参数是在服务器端配置文件配的，还有副本也是
		
		// 用来指定partitioner
		// props.put("partitioner.class", "top.lilixin.PartitionerDemo");
		// ����acknowledgement���ƣ�������fire and forget�����ܻ��������ݶ�ʧ
		// ֵΪ0,1,-1,���Բο�
		// http://kafka.apache.org/08/configuration.html
		ProducerConfig config = new ProducerConfig(props);
		producer = new Producer<String, String>(config);
	}

	/**
	 * �����������.
	 *
	 * @param topic
	 * @param msg
	 *            the msg
	 * @return the string
	 */
	public String send(String topic, String msg) {
		// Long start = System.currentTimeMillis();
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, msg);
		producer.send(data);
		// log.info("������Ϣ��ʱ��{}",System.currentTimeMillis()- start);
		return "ok";
	}
}