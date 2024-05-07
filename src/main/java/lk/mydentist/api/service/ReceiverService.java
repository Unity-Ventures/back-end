package lk.mydentist.api.service;

import lk.mydentist.api.dto.ReceiverDto;

import java.util.List;

public interface ReceiverService {

    ReceiverDto saveReceiver(ReceiverDto receiverDto);

    List<ReceiverDto> getAllReceivers();

    ReceiverDto updateStudent(Long receiverId, ReceiverDto updateDto);

    ReceiverDto deleteReceiver(Long receiverId);
}
