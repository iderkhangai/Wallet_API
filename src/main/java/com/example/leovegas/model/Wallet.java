package com.example.leovegas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wallet {
    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;
    private String currencyCode;
    private Double balance;
    @OneToOne(mappedBy = "wallet", cascade = CascadeType.ALL)
    private Player player;
    private Date createdAt;
}
