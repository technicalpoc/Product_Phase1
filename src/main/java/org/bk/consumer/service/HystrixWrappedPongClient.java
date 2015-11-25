package org.bk.consumer.service;

import org.bk.consumer.feign.InventoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service("hystrixInventoryClient")
public class HystrixWrappedPongClient implements InventoryClient {

    @Autowired
    @Qualifier("inventoryClient")
    private InventoryClient feignInventoryClient;

    @Override
    @HystrixCommand(groupKey = "inventoryGroup", fallbackMethod = "fallBackCall")
    public String sendMessage(String reqMessage) {
    	String response=null;
    	try{
    	 response = this.feignInventoryClient.sendMessage(reqMessage);
    	System.out.println("RESPONSE : "+response);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return response;
    }

    public String fallBackCall(String message) {
        String fallback = "FAILED SERVICE CALL! - FALLING BACK";
        return fallback;
    }
}
