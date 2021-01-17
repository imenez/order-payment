package com.imenez.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class HystrixController {

    @GetMapping("/order")
    public String orderFallBack(){
        return "Order service is not available :( ";
    }

    @GetMapping("/payment")
    public String paymentFallBack(){
        return "Payment service is not available :( ";
    }
}
