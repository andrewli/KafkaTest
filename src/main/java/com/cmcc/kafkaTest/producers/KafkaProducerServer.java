package com.cmcc.kafkaTest.producers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.alibaba.fastjson.JSON;
import com.cmcc.kafkaTest.KafkaMesConstant;

@Component("kafkaProducerServer")
public class KafkaProducerServer {
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	public Map<String,Object> sendMesForTemplate(String topic, Object value, String ifPartition, 
            Integer partitionNum, String role){
        String key = role+"-"+value.hashCode();
        String valueString = JSON.toJSONString(value);
        if(ifPartition.equals("0")){
            //表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partitionIndex, key, valueString);
            Map<String,Object> res = checkProRecord(result);
            return res;
        }else{
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, valueString);
            Map<String,Object> res = checkProRecord(result);
            return res;
        }
    }
	
	private int getPartitionIndex(String key, int partitionNum){
        if (key == null) {
            Random random = new Random();
            return random.nextInt(partitionNum);
        }
        else {
            int result = Math.abs(key.hashCode())%partitionNum;
            return result;
        }
    }
	
	@SuppressWarnings("rawtypes")
    private Map<String,Object> checkProRecord(ListenableFuture<SendResult<String, String>> res){
        Map<String,Object> m = new HashMap<String,Object>();
        if(res!=null){
            try {
                SendResult r = res.get();//检查result结果集
                /*检查recordMetadata的offset数据，不检查producerRecord*/
                Long offsetIndex = r.getRecordMetadata().offset();
                if(offsetIndex!=null && offsetIndex>=0){
                    m.put("code", KafkaMesConstant.SUCCESS_CODE);
                    m.put("message", KafkaMesConstant.SUCCESS_MES);
                    return m;
                }else{
                    m.put("code", KafkaMesConstant.KAFKA_NO_OFFSET_CODE);
                    m.put("message", KafkaMesConstant.KAFKA_NO_OFFSET_MES);
                    return m;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
                return m;
            } catch (ExecutionException e) {
                e.printStackTrace();
                m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
                return m;
            }
        }else{
            m.put("code", KafkaMesConstant.KAFKA_NO_RESULT_CODE);
            m.put("message", KafkaMesConstant.KAFKA_NO_RESULT_MES);
            return m;
        }
    }

}
