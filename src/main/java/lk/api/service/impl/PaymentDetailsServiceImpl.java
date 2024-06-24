package lk.api.service.impl;

import lk.api.dto.PaymentDetailsDto;
import lk.api.dto.getdto.PaymentDetailsGetDto;
import lk.api.model.PaymentDetails;
import lk.api.repository.PaymentDetailsRepo;
import lk.api.service.PaymentDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final PaymentDetailsRepo paymentDetailsRepo;
    private final ModelMapper modelMapper;

    public PaymentDetailsServiceImpl(PaymentDetailsRepo paymentDetailsRepo, ModelMapper modelMapper) {
        this.paymentDetailsRepo = paymentDetailsRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PaymentDetailsDto savePaymentDetails(PaymentDetailsDto paymentDetailsDto) {
        PaymentDetails paymentDetails = this.dtoToEntity(paymentDetailsDto);
        paymentDetails.setRunner(null);
        PaymentDetails save = this.paymentDetailsRepo.save(paymentDetails);
        return entityToDto(save);
    }

    @Override
    public List<PaymentDetailsGetDto> getAllPaymentDetails() {
        List<PaymentDetails> allPaymentDetails = this.paymentDetailsRepo.findAllPaymentDetails();
        List<PaymentDetailsGetDto> list = new ArrayList<>();
        for (PaymentDetails paymentDetails : allPaymentDetails) {
            PaymentDetailsGetDto dto = entityToGetDto(paymentDetails);
            list.add(dto);
        }
        return list;
    }

    @Override
    public PaymentDetailsGetDto updatePaymentDetails(Long paymentId, PaymentDetailsDto paymentDetailsDto) {
        Optional<PaymentDetails> byId = paymentDetailsRepo.findById(paymentId);
        if (byId.isPresent()) {
            paymentDetailsDto.setPaymentId(byId.get().getPaymentId());
            PaymentDetails paymentDetails = this.dtoToEntity(paymentDetailsDto);
            PaymentDetails save = paymentDetailsRepo.save(paymentDetails);
            return entityToGetDto(save);
        } else {
            return null;
        }
    }

    @Override
    public PaymentDetailsGetDto deletePaymentDetails(Long paymentId) {
        Optional<PaymentDetails> byId = paymentDetailsRepo.findById(paymentId);
        if (byId.isPresent()) {
            paymentDetailsRepo.deleteById(paymentId);
            return entityToGetDto(byId.get());
        } else {
            return null;
        }
    }

    @Override
    public PaymentDetailsGetDto searchPaymentDetails(Long paymentId) {
        Optional<PaymentDetails> byId = this.paymentDetailsRepo.findById(paymentId);
        return byId.map(this::entityToGetDto).orElse(null);
    }

    @Override
    public List<PaymentDetailsGetDto> getPaymentDetailsEmployeeWise(Long employeeId) {
        List<PaymentDetails> allPaymentDetails = this.paymentDetailsRepo.findAllByEmployeeEmployeeId(employeeId);
        List<PaymentDetailsGetDto> list = new ArrayList<>();
        for (PaymentDetails paymentDetails : allPaymentDetails) {
            PaymentDetailsGetDto dto = entityToGetDto(paymentDetails);
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<PaymentDetailsGetDto> searchPaymentDetailsOrderWise(Long orderId) {
        List<PaymentDetails> allPaymentDetails = this.paymentDetailsRepo.findAllByOrder(orderId);
        List<PaymentDetailsGetDto> list = new ArrayList<>();
        for (PaymentDetails paymentDetails : allPaymentDetails) {
            PaymentDetailsGetDto dto = entityToGetDto(paymentDetails);
            list.add(dto);
        }
        return list;
    }

    @Override
    public PaymentDetailsGetDto saveBillImage(Long paymentId, MultipartFile image) throws URISyntaxException, IOException {
        Optional<PaymentDetails> byId = paymentDetailsRepo.findById(paymentId);
        if (byId.isPresent()) {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadDir = new File(projectPath + "/uploads");
            uploadDir.mkdir();
            image.transferTo(new File(uploadDir.getAbsolutePath() + "/" + image.getOriginalFilename()));

            //set image path and name
            String bilImage = "uploads/" + image.getOriginalFilename();
            byId.get().setImage(bilImage);
            PaymentDetails save = paymentDetailsRepo.save(byId.get());
            return entityToGetDto(save);
        } else {
            return null;
        }
    }

    private PaymentDetails dtoToEntity(PaymentDetailsDto dto) {
        PaymentDetails paymentDetails = modelMapper.map(dto, PaymentDetails.class);
        paymentDetails.setPaymentId(dto.getPaymentId());
        return paymentDetails;
    }

    private PaymentDetailsDto entityToDto(PaymentDetails paymentDetails) {
        return (paymentDetails == null) ? null : modelMapper.map(paymentDetails, PaymentDetailsDto.class);
    }

    private PaymentDetailsGetDto entityToGetDto(PaymentDetails paymentDetails) {
        PaymentDetailsGetDto map = modelMapper.map(paymentDetails, PaymentDetailsGetDto.class);
        map.setPaymentId(paymentDetails.getPaymentId());
        return map;
    }
}
