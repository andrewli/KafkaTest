package com.cmcc.kafkaTest.zookeeperTest;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;

public class ReadZKClient {
	@Test
    public void testGetFromZkClient() throws InterruptedException {
        ZkClient zkClient = new ZkClient("localhost:2181,localhost:2182,localhost:2183");
        String node = "/app2/app23";
        while(true){
	        if (zkClient.exists(node)) {
	        	String result = zkClient.readData(node).toString();
	        	System.out.println("read data--->"+result);
	        }else{
	        	System.out.println("no nodePath exist!");
	        }
	        Thread.sleep(5000);
        }
    }
	
	@Test
    public void testGetFromZkClient_1() throws InterruptedException {
        ZkClient zkClient = new ZkClient("localhost:2181,localhost:2182,localhost:2183");
        String node = "/app2";
        if (zkClient.exists(node)) {
        	List<String> childNodes = zkClient.getChildren(node);
        	for(String child: childNodes){
        		System.out.println("childNode:--->"+child);
        	}
        }else{
        	System.out.println("no nodePath exist!");
        }
        Thread.sleep(5000);
    }
}
