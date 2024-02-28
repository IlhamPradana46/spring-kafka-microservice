package com.microservicespring.orderservice.controller;

import com.microservicespring.basedomain.dto.Order;
import com.microservicespring.basedomain.dto.OrderEvent;
import com.microservicespring.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order[] orders){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is pending");

        List<Order> orderList = Arrays.asList(orders);
        orderEvent.setOrder(orderList);

        orderProducer.sendMessage(orderEvent);

        return "order placed successfully";

    }
}
