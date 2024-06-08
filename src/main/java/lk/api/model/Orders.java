package lk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String status;
    private LocalDate date;
    private double amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<PaymentDetails> paymentDetails;
}
