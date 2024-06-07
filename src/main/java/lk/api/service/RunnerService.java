package lk.api.service;

import lk.api.dto.RunnerDto;

import java.util.List;

public interface RunnerService {

    RunnerDto saveRunner(RunnerDto runnerDto);

    List<RunnerDto> getAllRunners();

    RunnerDto updateRunner(Long runnerId, RunnerDto updateRunnerDto);

    RunnerDto deleteRunner(Long runnerId);

    RunnerDto searchRunner(Long runnerId);
}
