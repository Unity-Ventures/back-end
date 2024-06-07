package lk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String fistName;
    private String lastName;
    private String address;
    private String contact;
    private String nic;
    private String country;
    private String role;
    private String userName;
    private String password;

    public Employee(Long employeeId, String fistName, String lastName, String address, String contact, String nic, String country, String role, String userName, String password) {
        this.employeeId = employeeId;
        this.fistName = fistName;
        this.lastName = lastName;
        this.address = address;
        this.contact = contact;
        this.nic = nic;
        this.country = country;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Customer> customers;
}
