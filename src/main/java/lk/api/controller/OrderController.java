package lk.api.controller;

import lk.api.dto.OrderDto;
import lk.api.dto.getdto.OrderGetDto;
import lk.api.dto.getdto.PaymentDetailsGetDto;
import lk.api.service.OrderService;
import lk.api.service.PaymentDetailsService;
import lk.api.util.JWTTokenGenerator;
import lk.api.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final PaymentDetailsService paymentDetailsService;
    private final JWTTokenGenerator jwtTokenGenerator;

    public OrderController(OrderService orderService, PaymentDetailsService paymentDetailsService, JWTTokenGenerator jwtTokenGenerator) {
        this.orderService = orderService;
        this.paymentDetailsService = paymentDetailsService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody OrderDto orderDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            Random random = new Random();
            int fiveDigitNumber = 10000 + random.nextInt(90000);
            orderDto.setReferenceNo(fiveDigitNumber);
            OrderDto dto = this.orderService.saveOrder(orderDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllOrders(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<OrderGetDto> allOrders = this.orderService.getAllOrders();
            List<OrderGetDto> newList = new ArrayList<>();
            for (OrderGetDto dto : allOrders) {
                List<PaymentDetailsGetDto> paymentDetailsGetDtos = paymentDetailsService.searchPaymentDetailsOrderWise(dto.getOrderId());
                dto.setPaymentDetailsGetDto(paymentDetailsGetDtos);
                newList.add(dto);
            }
            return new ResponseEntity<>(newList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get_order_customer_wise/{customerId}")
    public ResponseEntity<Object> getAllOrdersCustomerWise(@PathVariable Long customerId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<OrderGetDto> allOrders = this.orderService.getAllOrdersCustomerWise(customerId);
            List<OrderGetDto> newList = new ArrayList<>();
            for (OrderGetDto dto : allOrders) {
                List<PaymentDetailsGetDto> paymentDetailsGetDtos = paymentDetailsService.searchPaymentDetailsOrderWise(dto.getOrderId());
                dto.setPaymentDetailsGetDto(paymentDetailsGetDtos);
                newList.add(dto);
            }
            return new ResponseEntity<>(newList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get_order_receiver_wise/{customerId}")
    public ResponseEntity<Object> getAllOrdersReceiverWise(@PathVariable Long customerId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<OrderGetDto> allOrders = this.orderService.getAllOrdersReceiverWise(customerId);
            List<OrderGetDto> newList = new ArrayList<>();
            for (OrderGetDto dto : allOrders) {
                List<PaymentDetailsGetDto> paymentDetailsGetDtos = paymentDetailsService.searchPaymentDetailsOrderWise(dto.getOrderId());
                dto.setPaymentDetailsGetDto(paymentDetailsGetDtos);
                newList.add(dto);
            }
            return new ResponseEntity<>(newList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> searchOrder(@PathVariable Long orderId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            OrderGetDto dto = this.orderService.searchOrder(orderId);
            List<PaymentDetailsGetDto> paymentDetailsGetDtos = paymentDetailsService.searchPaymentDetailsOrderWise(dto.getOrderId());
            dto.setPaymentDetailsGetDto(paymentDetailsGetDtos);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto updateDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            OrderGetDto dto = this.orderService.updateOrder(orderId, updateDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/updateState/{orderId}")
    public ResponseEntity<Object> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderDto status, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            OrderGetDto dto = this.orderService.updateOrderStatus(orderId, status);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long orderId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            OrderGetDto dto = this.orderService.deleteOrder(orderId);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
