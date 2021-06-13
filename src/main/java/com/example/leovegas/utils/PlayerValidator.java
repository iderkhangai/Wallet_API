package com.example.leovegas.utils;

import com.example.leovegas.dto.TransactionDto;
import com.example.leovegas.exception.IsNotExistException;
import com.example.leovegas.model.Player;
import com.example.leovegas.repository.PlayerRepository;
import com.example.leovegas.service.GlobalValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Optional;

@Component
public class PlayerValidator {
    private static final Logger logger = LoggerFactory.getLogger(PlayerValidator.class);
    @Autowired
    private PlayerRepository playerRepository;

    public boolean validate(TransactionDto transactionDTO) throws IsNotExistException {
        logger.debug("Checking player is valid: " + transactionDTO.getPlayerName());
        Player player = playerRepository.findPlayerByName(transactionDTO.getPlayerName());
        logger.debug("Found player: " + player);
        Optional<Player> pr = Optional.ofNullable(player);
        if (pr.isPresent()) {
            logger.info("Player is valid");
            return true;
        } else {
            logger.error("This player is not registered in the system!");
            throw new IsNotExistException("This player is not registered in the system!");
        }

    }
}
