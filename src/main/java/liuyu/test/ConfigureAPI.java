package liuyu.test;

public class ConfigureAPI {

	public interface KafkaProperties {
        public final static String ZK = "192.168.0.99:2181,192.168.0.98:2181,192.168.0.96:2181";
        public final static String GROUP_ID = "test_group1";
        public final static String TOPIC = "liuyu1";
        public final static String BROKER_LIST = "192.168.0.99:9092,192.168.0.98:9092,192.168.0.96:9092";
        public final static int BUFFER_SIZE = 64 * 1024;
        public final static int TIMEOUT = 20000;
        public final static int INTERVAL = 10000;
        
        
    }
}
