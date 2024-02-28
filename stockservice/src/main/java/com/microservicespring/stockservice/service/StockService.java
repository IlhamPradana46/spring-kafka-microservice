package com.microservicespring.stockservice.service;

import com.microservicespring.basedomain.dto.Order;
import com.microservicespring.basedomain.dto.OrderEvent;

import com.microservicespring.stockservice.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StockService{

    @Autowired
    private StockRepository stockRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    public void decreaseStock(OrderEvent event){
        List<Order> orders = event.getOrder();

        for(Order order : orders){
            Long getId = order.getOrderId();
            int getQty = order.getQty();

            stockRepository.findById(getId).ifPresentOrElse(stock -> {
                stock.setQty(stock.getQty() - getQty);
                stockRepository.save(stock);
            }, () -> {
                System.out.println("Decrease stock : -" + getId + "with id : " + getQty);
            });
        }
    }
}
