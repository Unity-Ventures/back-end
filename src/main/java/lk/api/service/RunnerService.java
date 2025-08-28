package lk.api.service;

import lk.api.dto.RunnerDto;
import lk.api.dto.getdto.RunnerGetDto;

import java.util.List;

public interface RunnerService {

    RunnerDto saveRunner(RunnerDto runnerDto);

    List<RunnerGetDto> getAllRunners();

    RunnerGetDto updateRunner(Long runnerId, RunnerDto updateRunnerDto);

    RunnerGetDto deleteRunner(Long runnerId);

    RunnerGetDto searchRunner(Long runnerId);

    List<RunnerGetDto> getAllRunnersEmployeeWise(Long employeeId);
}
