package kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.lilixin.TopProducer;
import top.lilixin.TopReceiver;

@ContextConfiguration({"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCase {
	
	@Autowired
	private TopProducer topProducer;
	
	@Autowired
	private TopReceiver topReceiver;
	
	
	private String topic = "lilixin3";

	@Test
	public void testCase(){
		System.out.println("##############################");
		topProducer.send(topic,"this ia a kafka test msg");
		System.out.println("##############################");
	}
	
	
}
