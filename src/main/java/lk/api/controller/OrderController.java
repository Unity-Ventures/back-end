package lk.api.controller;

import lk.api.dto.OrderDto;
import lk.api.service.OrderService;
import lk.api.util.JWTTokenGenerator;
import lk.api.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final JWTTokenGenerator jwtTokenGenerator;

    public OrderController(OrderService orderService, JWTTokenGenerator jwtTokenGenerator) {
        this.orderService = orderService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody OrderDto orderDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            OrderDto dto = this.orderService.saveOrder(orderDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllOrders(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<OrderDto> allOrders = this.orderService.getAllOrders();
            return new ResponseEntity<>(allOrders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto updateDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            OrderDto dto = this.orderService.updateOrder(orderId, updateDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long orderId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            OrderDto dto = this.orderService.deleteOrder(orderId);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
