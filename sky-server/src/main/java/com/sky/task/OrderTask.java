package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: OrderTask
 * Package: com.sky.task
 * Description:
 * Scheduled task class for processing order status periodically.
 * @Author Jingran Liu
 * @Create 2025/1/30 18:03
 * @Version 1.0
 */
@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;
    /**
     * Method for handling overdue orders.
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeoutOrder(){
        log.info("Scheduled task for handling overdue orders: {}", LocalDateTime.now());

        // select * from orders where status = ? and order_time < (now - 15)
        LocalDateTime orderTime = LocalDateTime.now().plusMinutes(-15);

        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, orderTime);

        if(ordersList != null && ordersList.size() != 0){
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("orders overdue, canceled automatically");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }

    }

    /**
     * Method for handling orders whose status is always on delivery
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder(){
        log.info("handling orders whose status is always on delivery: {}", LocalDateTime.now());

        LocalDateTime orderTime = LocalDateTime.now().plusMinutes(-60);

        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, orderTime);

        if(ordersList != null && ordersList.size() != 0){
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.COMPLETED);

                orderMapper.update(orders);
            }
        }
    }
}
