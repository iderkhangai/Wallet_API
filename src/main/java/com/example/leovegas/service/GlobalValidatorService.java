package com.example.leovegas.service;

import com.example.leovegas.controller.WalletController;
import com.example.leovegas.dto.TransactionDto;
import com.example.leovegas.utils.MinimumBalanceValidator;
import com.example.leovegas.utils.PlayerValidator;
import com.example.leovegas.utils.TransactionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class GlobalValidatorService {
    private static final Logger logger = LoggerFactory.getLogger(GlobalValidatorService.class);

    @Autowired
    TransactionValidator transactionValidator;

    @Autowired
    PlayerValidator playerValidator;

    @Autowired
    MinimumBalanceValidator minimumBalanceValidator;

    public boolean validateTransaction(TransactionDto transactionDto) {
        logger.debug("validateTransaction() is called: " + transactionDto.getPlayerName());
        transactionValidator.validate(transactionDto);
        return true;
    }

    public boolean validateBalance(TransactionDto transactionDto) {
        logger.debug("validateBalance() is called: " + transactionDto.getPlayerName());
        minimumBalanceValidator.validate(transactionDto);
        return true;
    }

    public boolean validatePlayer(TransactionDto transactionDto) {
        logger.debug("validatePlayer() is called: " + transactionDto.getPlayerName());
        playerValidator.validate(transactionDto);
        return true;
    }


}
