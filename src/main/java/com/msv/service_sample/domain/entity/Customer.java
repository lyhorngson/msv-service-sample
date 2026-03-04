package com.msv.service_sample.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "customers",
        indexes = {
                @Index(name = "idx_customers_email", columnList = "email"),
                @Index(name = "idx_customers_phone", columnList = "phone_number")
        }
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true, length = 30)
    private String phoneNumber;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 20)
//    private CustomerStatus status;

    @Column(length = 500)
    private String note;
}