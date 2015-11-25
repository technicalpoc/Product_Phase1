package org.bk.consumer.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("sample-inventory")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/inventory",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    String sendMessage(@RequestParam("productName") String productName);
}