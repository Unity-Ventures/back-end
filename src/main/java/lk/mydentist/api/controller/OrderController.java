package lk.mydentist.api.controller;

import lk.mydentist.api.dto.OrderDto;
import lk.mydentist.api.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody OrderDto orderDto){
        OrderDto dto = this.orderService.saveOrder(orderDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllOrders(){
        List<OrderDto> dtos = this.orderService.getAllOrders();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public  ResponseEntity<Object> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto updateDto){
        OrderDto dto = this.orderService.updateOrder(orderId,updateDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long orderId){
        OrderDto dto = this.orderService.deleteOrder(orderId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
