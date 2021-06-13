package com.example.leovegas.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "currency_code")
    private String currencyCode;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    private Player player;
    @Column(name = "amount")
    private Long amount;
}
