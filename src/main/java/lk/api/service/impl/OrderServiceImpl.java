package lk.api.service.impl;

import lk.api.dto.OrderDto;
import lk.api.dto.getdto.OrderGetDto;
import lk.api.model.Orders;
import lk.api.repository.OrderRepo;
import lk.api.service.OrderService;
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
        orderDto.setStatus("pending");
        Orders orders = this.dtoToEntity(orderDto);
        Orders save = orderRepo.save(orders);
        return entityToDto(save);
    }

    @Override
    public List<OrderGetDto> getAllOrders() {
        List<Orders> allOrders = orderRepo.findAllOrders();
        List<OrderGetDto> list = new ArrayList<>();
        for (Orders orders : allOrders){
            OrderGetDto orderDto = entityToGetDto(orders);
            list.add(orderDto);
        }
        return list;
    }

    @Override
    public OrderGetDto updateOrder(Long orderId, OrderDto updateDto) {
        Optional<Orders> byId = orderRepo.findById(orderId);
        if (byId.isPresent()){
            updateDto.setOrderId(orderId);
            Orders orders = this.dtoToEntity(updateDto);
            Orders save = orderRepo.save(orders);
            return entityToGetDto(save);
        }else {
            return null;
        }
    }

    @Override
    public OrderGetDto deleteOrder(Long orderId) {
        Optional<Orders> byId = orderRepo.findById(orderId);
        if (byId.isPresent()){
            orderRepo.deleteById(orderId);
            return entityToGetDto(byId.get());
        }else {
            return null;
        }
    }

    @Override
    public OrderGetDto updateOrderStatus(Long orderId, OrderDto dto) {
        Optional<Orders> byId = orderRepo.findById(orderId);
        if (byId.isPresent()){
            byId.get().setStatus(dto.getStatus());
            Orders save = orderRepo.save(byId.get());
            return entityToGetDto(save);
        }else {
            return null;
        }
    }

    @Override
    public OrderGetDto searchOrder(Long orderId) {
        Optional<Orders> byId = orderRepo.findById(orderId);
        return byId.map(this::entityToGetDto).orElse(null);
    }

    @Override
    public List<OrderGetDto> getAllOrdersCustomerWise(Long customerId) {
        List<Orders> allOrders = orderRepo.findAllByCustomerCustomerId(customerId);
        List<OrderGetDto> list = new ArrayList<>();
        for (Orders orders : allOrders){
            OrderGetDto orderDto = entityToGetDto(orders);
            list.add(orderDto);
        }
        return list;
    }

    private Orders dtoToEntity(OrderDto orderDto){
        Orders map = modelMapper.map(orderDto, Orders.class);
        map.setOrderId(orderDto.getOrderId());
        return map;
    }

    private OrderDto entityToDto(Orders orders){
        return (orders == null) ? null : modelMapper.map(orders, OrderDto.class);
    }

    private OrderGetDto entityToGetDto(Orders orders){
        return (orders == null) ? null : modelMapper.map(orders, OrderGetDto.class);
    }
}
