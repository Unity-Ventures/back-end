package lk.mydentist.api.controller;

import lk.mydentist.api.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class EmployeeController {

    @PostMapping("/login")
    public Map<String, String> employeeLogin(@RequestBody EmployeeDto dto){
        return null;
    }
}
