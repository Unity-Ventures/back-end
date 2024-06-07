package lk.mydentist.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "runners")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long runnerId;
    private String name;
    private String address;
    private String contact;
    private String nic;
    private String country;
    private String userName;
    private String password;
}
