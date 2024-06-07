package lk.api.controller;

import lk.api.dto.EmployeeDto;
import lk.api.service.EmployeeService;
import lk.api.util.JWTTokenGenerator;
import lk.api.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class EmployeeController {

    private final JWTTokenGenerator jwtTokenGenerator;
    private final EmployeeService employeeService;

    public EmployeeController(JWTTokenGenerator jwtTokenGenerator, EmployeeService employeeService) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public Map<String, String> employeeLogin(@RequestBody EmployeeDto dto) {
        EmployeeDto employeeDto = employeeService.employeeLogin(dto);
        Map<String, String> response = new HashMap<>();
        if (employeeDto == null) {
            response.put("massage", "wrong details");
        } else {
            String token = this.jwtTokenGenerator.generateToken(employeeDto);
            response.put("token", token);
        }
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody EmployeeDto dto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {

            EmployeeDto isUser = this.employeeService.findUserByName(dto.getNic(), dto.getUserName());
            if (isUser == null) {
                EmployeeDto employeeDto = this.employeeService.registerUser(dto);
                return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("User is exist", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/get_user_info_by_token")
    public ResponseEntity<Object> getUserInfoByToken(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            EmployeeDto employeeFromToken = this.jwtTokenGenerator.getEmployeeFromToken(authorizationHeader);
            return new ResponseEntity<>(employeeFromToken, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto dto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            EmployeeDto employeeDto = this.employeeService.updateEmployee(dto, employeeId);
            return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get_all_user")
    public ResponseEntity<Object> getAllEmployee(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<EmployeeDto> allUsers = this.employeeService.getAllEmployee();
            return new ResponseEntity<>(allUsers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
