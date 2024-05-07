package lk.mydentist.api.service.impl;

import lk.mydentist.api.dto.ReceiverDto;
import lk.mydentist.api.model.Receiver;
import lk.mydentist.api.repository.ReceiverRepo;
import lk.mydentist.api.service.ReceiverService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiverServiceImpl implements ReceiverService {

    private final ReceiverRepo receiverRepo;
    private final ModelMapper modelMapper;

    public ReceiverServiceImpl(ReceiverRepo receiverRepo, ModelMapper modelMapper) {
        this.receiverRepo = receiverRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReceiverDto saveReceiver(ReceiverDto receiverDto) {
        Receiver receiver = this.dtoToEntity(receiverDto);
        Receiver save = receiverRepo.save(receiver);
        return entityToDto(save);
    }

    @Override
    public List<ReceiverDto> getAllReceivers() {
        List<Receiver> allReceiver = receiverRepo.findAllReceiver();
        List<ReceiverDto> list = new ArrayList<>();
        for (Receiver receiver : allReceiver){
            ReceiverDto receiverDto = entityToDto(receiver);
            list.add(receiverDto);
        }
            return list;
    }

    @Override
    public ReceiverDto updateStudent(Long receiverId, ReceiverDto updateDto) {
        Optional<Receiver> byId = receiverRepo.findById(receiverId);
        if (byId.isPresent()){
            updateDto.setReceiverId(receiverId);
            Receiver receiver = this.dtoToEntity(updateDto);
            Receiver save = receiverRepo.save(receiver);
            return entityToDto(save);
        }else {
            return null;
        }
    }

    @Override
    public ReceiverDto deleteReceiver(Long receiverId) {
        Optional<Receiver> byId = receiverRepo.findById(receiverId);
        if (byId.isPresent()){
            receiverRepo.deleteById(receiverId);
            return entityToDto(byId.get());
        }else {
            return null;
        }
    }

    private Receiver dtoToEntity(ReceiverDto receiverDto){
        Receiver map = modelMapper.map(receiverDto, Receiver.class);
        map.setReceiverId(receiverDto.getReceiverId());
        return map;
    }
    private ReceiverDto entityToDto(Receiver receiver){
        return (receiver == null) ? null : modelMapper.map(receiver, ReceiverDto.class);
    }
}
