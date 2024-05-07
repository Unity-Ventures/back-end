package lk.mydentist.api.controller;

import lk.mydentist.api.dto.ReceiverDto;
import lk.mydentist.api.service.ReceiverService;
import lk.mydentist.api.util.JWTTokenGenerator;
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
    public ResponseEntity<Object> saveReceiver(@RequestBody ReceiverDto receiverDto){
        ReceiverDto dto = this.receiverService.saveReceiver(receiverDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllReceiver(){
        List<ReceiverDto> dtos = this.receiverService.getAllReceivers();
        return new ResponseEntity<>(dtos, HttpStatus.CREATED);
    }

    @PutMapping("/{receiverId}")
    public  ResponseEntity<Object> updateReceiver(@PathVariable Long receiverId, @RequestBody ReceiverDto updateDto){
        ReceiverDto dto = this.receiverService.updateStudent(receiverId,updateDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{receiverId}")
    public ResponseEntity<Object> deleteReceiver(@PathVariable Long receiverId){
        ReceiverDto dto = this.receiverService.deleteReceiver(receiverId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
