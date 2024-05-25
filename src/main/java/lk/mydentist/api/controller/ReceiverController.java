package lk.mydentist.api.controller;

import lk.mydentist.api.dto.ReceiverDto;
import lk.mydentist.api.service.ReceiverService;
import lk.mydentist.api.util.JWTTokenGenerator;
import lk.mydentist.api.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/receiver")
public class ReceiverController {
    private final ReceiverService receiverService;
    private final JWTTokenGenerator jwtTokenGenerator;

    public ReceiverController(ReceiverService receiverService, JWTTokenGenerator jwtTokenGenerator) {
        this.receiverService = receiverService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping
    public ResponseEntity<Object> saveReceiver(@RequestBody ReceiverDto receiverDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            ReceiverDto dto = this.receiverService.saveReceiver(receiverDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllReceiver(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<ReceiverDto> allReceivers = this.receiverService.getAllReceivers();
            return new ResponseEntity<>(allReceivers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{receiverId}")
    public ResponseEntity<Object> updateReceiver(@PathVariable Long receiverId, @RequestBody ReceiverDto updateDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            ReceiverDto dto = this.receiverService.updateStudent(receiverId, updateDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{receiverId}")
    public ResponseEntity<Object> deleteReceiver(@PathVariable Long receiverId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            ReceiverDto dto = this.receiverService.deleteReceiver(receiverId);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
