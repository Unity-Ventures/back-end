package lk.mydentist.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiverId;
    private String name;
    private String address;
    private String contact;
    private String nic;
    private String accountNo;
    private String bank;
    private String branch;
    private String country;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Orders> orders;
}
