package lk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String name;
    private String accountNo;
    private String bank;
    private String branch;
    private String country;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Orders> orders;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
