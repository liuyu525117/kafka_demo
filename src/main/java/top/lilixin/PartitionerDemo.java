package top.lilixin;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

//设定依据key将当前这条消息发送到哪个partition的规则
public class PartitionerDemo implements Partitioner{

	public PartitionerDemo(VerifiableProperties props) {  
        //注意 ： 构造函数的函数体没有东西，但是不能没有构造函数          
  }  
  
  public int partition(Object key, int numPartitions) {
      try {            
          long partitionNum = Long.parseLong((String) key);
          return (int) Math.abs(partitionNum % numPartitions);
      } catch (Exception e) {
          return Math.abs(key.hashCode() % numPartitions);
      }
  }
}
