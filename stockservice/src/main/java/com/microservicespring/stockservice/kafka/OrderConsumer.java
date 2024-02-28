package com.microservicespring.stockservice.kafka;

import com.microservicespring.basedomain.dto.OrderEvent;
import com.microservicespring.stockservice.service.StockService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class OrderConsumer {

    @Autowired
    private StockService stockService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event){
        LOGGER.info(String.format("Order event received in stock => %s", event.toString()));
        stockService.decreaseStock(event);

    }
}
