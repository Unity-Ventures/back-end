package lk.api.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private double runnerAmount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "runner_id")
    @NonNull
    private Runner runner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Orders order;
}
