package lk.api.service;

import lk.api.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto saveOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(Long orderId, OrderDto updateDto);

    OrderDto deleteOrder(Long orderId);
}
