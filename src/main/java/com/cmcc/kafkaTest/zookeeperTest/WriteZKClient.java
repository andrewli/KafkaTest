package com.cmcc.kafkaTest.zookeeperTest;

import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;

public class WriteZKClient {
	
	@Test
    public void testSaveToZkClient() {
        ZkClient zkClient = new ZkClient("localhost:2181,localhost:2182,localhost:2183");
        String node = "/app2/app23";
        if (!zkClient.exists(node)) {
            zkClient.createPersistent(node, "hello zk");
        }else{
        	zkClient.writeData(node, "write app211");
        }
//        System.out.println(zkClient.readData(node));
    }
	
	@Test
    public void testDeleteNodeZkClient() {
        ZkClient zkClient = new ZkClient("localhost:2181,localhost:2182,localhost:2183");
        String node = "/app2";
        if (zkClient.exists(node)) {
            zkClient.delete(node);
        }
//        System.out.println(zkClient.readData(node));
    }


	
}
