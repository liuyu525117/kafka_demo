package liuyu.test;

import liuyu.test.ConfigureAPI.KafkaProperties;

public class KafkaClient {

	public static void main(String[] args) {
        JProducer pro = new JProducer(KafkaProperties.TOPIC);
        pro.start();

        //JConsumer con = new JConsumer(KafkaProperties.TOPIC);
        JConsumer2 con = new JConsumer2(KafkaProperties.TOPIC);
        con.start();
    }
}
