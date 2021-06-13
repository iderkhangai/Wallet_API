package com.example.leovegas.utils;

import com.example.leovegas.dto.TransactionDto;
import com.example.leovegas.exception.TransactionDuplicatedException;
import com.example.leovegas.model.Transaction;
import com.example.leovegas.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Optional;

@Component
public class TransactionValidator {
    private static final Logger logger = LoggerFactory.getLogger(TransactionValidator.class);
    @Autowired
    private TransactionRepository transactionRepository;

    public boolean validate(TransactionDto transactionDTO) throws TransactionDuplicatedException {
        logger.debug("Transaction ID: " + transactionDTO.getTransactionId());
        Optional<Transaction> transaction = transactionRepository.findById(transactionDTO.getTransactionId());
        if (transaction.isPresent()) {
            throw new TransactionDuplicatedException("Transaction Id is not unique!");
        } else {
            return true;
        }

    }
}
