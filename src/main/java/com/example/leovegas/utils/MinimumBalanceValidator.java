package com.example.leovegas.utils;

import com.example.leovegas.dto.TransactionDto;
import com.example.leovegas.exception.MinimumBalanceException;
import com.example.leovegas.model.Player;
import com.example.leovegas.repository.PlayerRepository;
import com.example.leovegas.service.GlobalValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

@Component
public class MinimumBalanceValidator {
    @Autowired
    private PlayerRepository playerRepository;
    private static final Logger logger = LoggerFactory.getLogger(MinimumBalanceValidator.class);

    public boolean validate(TransactionDto transactionDTO) throws MinimumBalanceException {

        Player player = playerRepository.findPlayerByName(transactionDTO.getPlayerName());
        logger.debug("Player balance - " + player.getWallet().getBalance());
        if (player.getWallet().getBalance() >= Double.parseDouble(transactionDTO.getTransactionAmount())) {
            return true;
        } else
            throw new MinimumBalanceException("Insufficient Balance!");
    }
}
