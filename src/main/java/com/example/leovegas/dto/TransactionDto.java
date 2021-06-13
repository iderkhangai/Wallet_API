package com.example.leovegas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@JsonRootName("transaction")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    @NotEmpty(message = "Enter Transaction ID")
    private String transactionId;
    @NotEmpty(message = "Enter Player name!")
    private String playerName;
    @NotEmpty(message = "Enter Transaction amount!")
    private String transactionAmount;
    private String transactionType;
    private Date transactionDate;

    @Override
    public String toString() {
        return "TransactionDto{" +
                "transactionId='" + transactionId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", transactionAmount='" + transactionAmount + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
