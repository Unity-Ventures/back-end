package lk.api.service;

import lk.api.dto.OrderDto;
import lk.api.dto.getdto.OrderGetDto;

import java.util.List;

public interface OrderService {
    OrderDto saveOrder(OrderDto orderDto);

    List<OrderGetDto> getAllOrders();

    OrderGetDto updateOrder(Long orderId, OrderDto updateDto);

    OrderGetDto deleteOrder(Long orderId);

    OrderGetDto updateOrderStatus(Long orderId, OrderDto status);

    OrderGetDto searchOrder(Long orderId);

    List<OrderGetDto> getAllOrdersCustomerWise(Long customerId);

    List<OrderGetDto> getAllOrdersReceiverWise(Long customerId);
}
