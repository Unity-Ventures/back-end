package lk.mydentist.api.controller;

import lk.mydentist.api.dto.RunnerDto;
import lk.mydentist.api.service.RunnerService;
import lk.mydentist.api.util.JWTTokenGenerator;
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
    public ResponseEntity<Object> saveRunne(@RequestBody RunnerDto runnerDto){

            RunnerDto dto = this.runnerService.saveRunner(runnerDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllRunners(){
        List<RunnerDto> dtos = this.runnerService.getAllRunners();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{runnerId}")
    public  ResponseEntity<Object> updateStudent(@PathVariable Long runnerId, @RequestBody RunnerDto updateRunnerDto){
        RunnerDto dto = this.runnerService.updateRunner(runnerId,updateRunnerDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{runnerId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long runnerId){
        RunnerDto dto = this.runnerService.deleteRunner(runnerId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
