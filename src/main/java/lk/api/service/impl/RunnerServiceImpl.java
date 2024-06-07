package lk.api.service.impl;

import lk.api.dto.RunnerDto;
import lk.api.model.Runner;
import lk.api.repository.RunnerRepo;
import lk.api.service.RunnerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RunnerServiceImpl implements RunnerService {

    private final RunnerRepo runnerRepo;
    private final ModelMapper modelMapper;

    public RunnerServiceImpl(RunnerRepo runnerRepo, ModelMapper modelMapper) {
        this.runnerRepo = runnerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public RunnerDto saveRunner(RunnerDto runnerDto) {
        Runner runner = this.dtoToEntity(runnerDto);
        Runner save = this.runnerRepo.save(runner);
        return entityToDto(save);
    }

    @Override
    public List<RunnerDto> getAllRunners() {
        List<Runner> allRunners = this.runnerRepo.findAllRunners();
        List<RunnerDto> list = new ArrayList<>();
        for (Runner runner : allRunners){
            RunnerDto dto = entityToDto(runner);
            list.add(dto);
        }
        return list;
    }

    @Override
    public RunnerDto searchRunner(Long id) {
        Optional<Runner> byId = this.runnerRepo.findById(id);
        return byId.map(this::entityToDto).orElse(null);
    }

    @Override
    public RunnerDto updateRunner(Long runnerId, RunnerDto updateRunnerDto) {
        Optional<Runner> byId = runnerRepo.findById(runnerId);

        if (byId.isPresent()){
            updateRunnerDto.setRunnerId(runnerId);
            Runner runner = this.dtoToEntity(updateRunnerDto);
            Runner save = runnerRepo.save(runner);
            return entityToDto(save);
        }else {
            return null;
        }
    }

    @Override
    public RunnerDto deleteRunner(Long runnerId) {
        Optional<Runner> byId = runnerRepo.findById(runnerId);
        if (byId.isPresent()){
            runnerRepo.deleteById(runnerId);
            return entityToDto(byId.get());
        }else {
            return null;
        }
    }

    private Runner dtoToEntity(RunnerDto dto){
        Runner runner = modelMapper.map(dto, Runner.class);
        runner.setRunnerId(dto.getRunnerId());
        return runner;
    }
    private RunnerDto entityToDto(Runner runner){
        return(runner == null) ? null : modelMapper.map(runner, RunnerDto.class);
    }
}
