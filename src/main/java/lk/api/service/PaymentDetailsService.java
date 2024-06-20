package lk.api.service;

import lk.api.dto.PaymentDetailsDto;
import lk.api.dto.getdto.PaymentDetailsGetDto;

import java.util.List;

public interface PaymentDetailsService {
    PaymentDetailsDto savePaymentDetails(PaymentDetailsDto paymentDetailsDto);

    List<PaymentDetailsGetDto> getAllPaymentDetails();

    PaymentDetailsGetDto updatePaymentDetails(Long paymentId, PaymentDetailsDto paymentDetailsDto);

    PaymentDetailsGetDto deletePaymentDetails(Long paymentId);

    PaymentDetailsGetDto searchPaymentDetails(Long paymentId);

    List<PaymentDetailsGetDto> getPaymentDetailsEmployeeWise(Long employeeId);
}
