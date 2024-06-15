package lk.api.controller;

import lk.api.dto.PaymentDetailsDto;
import lk.api.dto.getdto.PaymentDetailsGetDto;
import lk.api.service.PaymentDetailsService;
import lk.api.util.JWTTokenGenerator;
import lk.api.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/payment_details")
public class PaymentDetailsController {
    private final JWTTokenGenerator jwtTokenGenerator;
    private final PaymentDetailsService paymentDetailsService;

    public PaymentDetailsController(JWTTokenGenerator jwtTokenGenerator, PaymentDetailsService paymentDetailsService) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.paymentDetailsService = paymentDetailsService;
    }

    @PostMapping
    public ResponseEntity<Object> savePaymentDetails(@RequestBody PaymentDetailsDto paymentDetailsDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            PaymentDetailsDto dto = paymentDetailsService.savePaymentDetails(paymentDetailsDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllPaymentDetails(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<PaymentDetailsGetDto> dtos = this.paymentDetailsService.getAllPaymentDetails();
            return new ResponseEntity<>(dtos, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Object> updatePaymentDetails(@PathVariable Long paymentId, @RequestBody PaymentDetailsDto paymentDetailsDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            PaymentDetailsGetDto dto = this.paymentDetailsService.updatePaymentDetails(paymentId, paymentDetailsDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Object> deletePaymentDetails(@PathVariable Long paymentId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            PaymentDetailsGetDto dto = this.paymentDetailsService.deletePaymentDetails(paymentId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search/{paymentId}")
    public ResponseEntity<Object> searchPaymentDetails(@PathVariable Long paymentId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            PaymentDetailsGetDto dto = this.paymentDetailsService.searchPaymentDetails(paymentId);
            if (dto == null) {
                return new ResponseEntity<>("Empty Data", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
