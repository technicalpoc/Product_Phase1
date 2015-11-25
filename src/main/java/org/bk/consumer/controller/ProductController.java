package org.bk.consumer.controller;

import org.bk.consumer.feign.InventoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("hystrixInventoryClient")
    private InventoryClient inventoryClient;

    
    /**
     * http://rashmitr:8080/
     * curl -X POST http://localhost:8080/refresh -- To get refreshed configuration from DynamicConfig server
     * 
     */
    
    @RequestMapping("/dispatch")
    public String sendMessage(@RequestBody String message) {
    	
    	String start = "{\"payload\":\"";
    	String end = "\"}";
    	String pName = message.substring(start.length(), message.indexOf(end));
    	
    	System.out.println("Request in ProductController :  "+pName);
        String response = this.inventoryClient.sendMessage(pName);
        
        //"{\"payload\":\"RESPONSE : REQUEST: Product-p1, RESPONSE : Available quantity - 10\"}";
        String jsonResponse = start + response + end;
        System.out.println("JSON => "+jsonResponse);
        return jsonResponse;
    }
}
