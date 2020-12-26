package liuyu.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import liuyu.test.ConfigureAPI.KafkaProperties;
import top.lilixin.TopReceiver;

/**
 * 注释
 * @author liuyu
 *
 */
public class JConsumer2 extends Thread {

	private Logger log = LoggerFactory.getLogger(JConsumer2.class);
	
	private ConsumerConnector consumer;
    private String topic;
    private final int SLEEP = 1000 * 3;

    public JConsumer2(String topic) {
    	// Create the connection to the cluster  
        consumer = Consumer.createJavaConsumerConnector(this.consumerConfig());
        this.topic = topic;
    }

    private ConsumerConfig consumerConfig() {
    	// specify some consumer properties 
        Properties props = new Properties();
        props.put("zookeeper.connect", KafkaProperties.ZK);
        props.put("group.id", KafkaProperties.GROUP_ID);
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        // create 4 partitions of the stream for topic “test-topic”, to allow 4 threads to consume
        topicCountMap.put(topic, new Integer(4));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
//        ConsumerIterator<byte[], byte[]> it = stream.iterator();
//        while (it.hasNext()) {
//            System.out.println("Receive->[" + new String(it.next().message()) + "]");
//            try {
//                sleep(SLEEP);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
        
        ExecutorService executor = Executors.newFixedThreadPool(4);  
        for(final KafkaStream<byte[], byte[]> stream : streams){
        	executor.submit(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					ConsumerIterator<byte[], byte[]> it = stream.iterator();
    		        while(it.hasNext()){
    		        	String msg = new String(it.next().message());
    		        	/*try{
    		        	}catch(Exception e){
    		        		log.info("kafka vkoConsumer topic:{} ", topic, msg,e);
    		        	}*/
    		        	log.info("kafka Consumer topic:{} ", topic, msg);
    		        	System.out.println("-----consumer message:  " + msg);
    		        }
				}  
        	});
        }
        
    }

}
