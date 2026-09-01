package com.camoutech.ebankservice.entities;

import com.camoutech.ebankservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccount {

    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String type;
    private long customerId;

    @Transient
    private Customer customer;
}
