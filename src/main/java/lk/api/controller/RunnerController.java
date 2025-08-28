package lk.api.controller;

import lk.api.dto.EmployeeDto;
import lk.api.dto.RunnerDto;
import lk.api.dto.getdto.RunnerGetDto;
import lk.api.service.RunnerService;
import lk.api.util.JWTTokenGenerator;
import lk.api.util.TokenStatus;
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
            EmployeeDto employeeDto = jwtTokenGenerator.getEmployeeFromToken(authorizationHeader);
            runnerDto.setEmployeeId(employeeDto.getEmployeeId());
            RunnerDto dto = this.runnerService.saveRunner(runnerDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllRunners(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<RunnerGetDto> allRunners = this.runnerService.getAllRunners();
            return new ResponseEntity<>(allRunners, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{runnerId}")
    public ResponseEntity<Object> searchRunners(@PathVariable Long runnerId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            RunnerGetDto dto = this.runnerService.searchRunner(runnerId);
            if (dto == null) {
                return new ResponseEntity<>("Empty Data", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("get_all_runner_employee_wise/{employeeId}")
    public ResponseEntity<Object> getAllRunnersEmployeeWise(@PathVariable Long employeeId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<RunnerGetDto> runnerList = this.runnerService.getAllRunnersEmployeeWise(employeeId);
            return new ResponseEntity<>(runnerList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{runnerId}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long runnerId, @RequestBody RunnerDto updateRunnerDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            RunnerGetDto dto = this.runnerService.updateRunner(runnerId, updateRunnerDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{runnerId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long runnerId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            RunnerGetDto dto = this.runnerService.deleteRunner(runnerId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
