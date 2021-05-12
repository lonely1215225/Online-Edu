package com.atguigu.serviceedu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("service-order")
public interface OrderClient {
    @GetMapping("/eduorder/order/isBuyCourse/{cmId}")
    Boolean isBuyCourse(@PathVariable(value = "cmId") String cmId);
}