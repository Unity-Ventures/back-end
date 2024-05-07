package lk.mydentist.api.service.impl;

import lk.mydentist.api.dto.OrderDto;
import lk.mydentist.api.model.Orders;
import lk.mydentist.api.repository.OrderRepo;
import lk.mydentist.api.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepo orderRepo, ModelMapper modelMapper) {
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        orderDto.setStatus(0);
        Orders orders = this.dtoToEntity(orderDto);
        Orders save = orderRepo.save(orders);
        return entityToDto(save);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Orders> allOrders = orderRepo.findAllOrders();
        List<OrderDto> list = new ArrayList<>();
        for (Orders orders : allOrders){
            OrderDto orderDto = entityToDto(orders);
            list.add(orderDto);
        }
        return list;
    }

    @Override
    public OrderDto updateOrder(Long orderId, OrderDto updateDto) {
        Optional<Orders> byId = orderRepo.findById(orderId);
        if (byId.isPresent()){
            updateDto.setOrderId(orderId);
            Orders orders = this.dtoToEntity(updateDto);
            Orders save = orderRepo.save(orders);
            return entityToDto(save);
        }else {
            return null;
        }
    }

    @Override
    public OrderDto deleteOrder(Long orderId) {
        Optional<Orders> byId = orderRepo.findById(orderId);
        if (byId.isPresent()){
            orderRepo.deleteById(orderId);
            return entityToDto(byId.get());
        }else {
            return null;
        }
    }

    private Orders dtoToEntity(OrderDto orderDto){
        Orders map = modelMapper.map(orderDto, Orders.class);
        map.setOrderId(orderDto.getOrderId());
        return map;
    }
    private OrderDto entityToDto(Orders orders){
        return (orders == null) ? null : modelMapper.map(orders, OrderDto.class);
    }
}
