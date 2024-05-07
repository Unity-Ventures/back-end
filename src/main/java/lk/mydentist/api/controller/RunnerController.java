package lk.mydentist.api.controller;

import lk.mydentist.api.dto.RunnerDto;
import lk.mydentist.api.service.RunnerService;
import lk.mydentist.api.util.JWTTokenGenerator;
import lk.mydentist.api.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/runner")
public class RunnerController {

    private final JWTTokenGenerator jwtTokenGenerator;
    private final RunnerService runnerService;

    public RunnerController(JWTTokenGenerator jwtTokenGenerator, RunnerService runnerService) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.runnerService = runnerService;
    }

    @PostMapping
    public ResponseEntity<Object> saveRunner(@RequestBody RunnerDto runnerDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            RunnerDto dto = this.runnerService.saveRunner(runnerDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllRunners(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<RunnerDto> allRunners = this.runnerService.getAllRunners();
            return new ResponseEntity<>(allRunners, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{runnerId}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long runnerId, @RequestBody RunnerDto updateRunnerDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            RunnerDto dto = this.runnerService.updateRunner(runnerId, updateRunnerDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{runnerId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long runnerId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            RunnerDto dto = this.runnerService.deleteRunner(runnerId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
