package lk.mydentist.api.service;

import lk.mydentist.api.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto saveOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(Long orderId, OrderDto updateDto);

    OrderDto deleteOrder(Long orderId);
}
