package lk.mydentist.api.service;

import lk.mydentist.api.dto.RunnerDto;

import java.util.List;

public interface RunnerService {

    RunnerDto saveRunner(RunnerDto runnerDto);

    List<RunnerDto> getAllRunners();

    RunnerDto updateRunner(Long runnerId, RunnerDto updateRunnerDto);

    RunnerDto deleteRunner(Long runnerId);
}
