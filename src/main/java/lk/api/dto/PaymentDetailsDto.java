package lk.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class PaymentDetailsDto {
    private Long paymentId;
    private Long employeeId;
    private Long runnerId;
    private Long orderId;
    private double runnerAmount;
    private MultipartFile image;
}
