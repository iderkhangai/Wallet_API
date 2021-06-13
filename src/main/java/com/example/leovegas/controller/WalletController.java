package com.example.leovegas.controller;

import com.example.leovegas.dto.TransactionDto;
import com.example.leovegas.dto.WalletDto;
import com.example.leovegas.exception.IsNotExistException;
import com.example.leovegas.exception.WalletDoesntExistException;
import com.example.leovegas.model.Player;
import com.example.leovegas.repository.PlayerRepository;
import com.example.leovegas.service.GlobalValidatorService;
import com.example.leovegas.service.TransactionService;
import com.example.leovegas.service.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/v1")
@Api(value = "Player's Wallet Service")
public class WalletController {
    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);
    @Autowired
    private WalletService walletService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private GlobalValidatorService validatorService;

    @GetMapping(value = "{playerName}/wallet")
    @ApiOperation(value = "Retrieve player's wallet details")
    public ResponseEntity<WalletDto> balance(@Valid @PathVariable("playerName") String playerName) throws IsNotExistException, WalletDoesntExistException {
        logger.debug("checkBalance() is called: \n" + playerName);
        return ResponseEntity.ok(walletService.getWallet(playerName));
    }

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping(value = "/wallet/credit")
    @ApiOperation(value = "Credit to player's wallet")
    public ResponseEntity<WalletDto> doCredit(@Valid @RequestBody TransactionDto transactionDTO) {
        logger.debug("doCredit() is called: \n" + transactionDTO.toString());
        validatorService.validatePlayer(transactionDTO);
        validatorService.validateTransaction(transactionDTO);
        WalletDto walletDTO = walletService.makeCredit(transactionDTO);

        return ResponseEntity.ok(walletDTO);
    }


    @PostMapping(value = "/wallet/debit")
    @ApiOperation(value = "Debit from player's account")
    public ResponseEntity<WalletDto> doDebit(@Valid @RequestBody TransactionDto transactionDTO) {
        logger.debug("Debit service is called: " + transactionDTO.toString());
        validatorService.validatePlayer(transactionDTO);
        validatorService.validateBalance(transactionDTO);
        WalletDto walletDTO = walletService.makeDebit(transactionDTO);

        return ResponseEntity.ok(walletDTO);
    }


    @GetMapping(value = "/{playerName}/wallet/history")
    @ApiOperation(value = "Retrieve Player's transaction history")
    public List<TransactionDto> history(@Valid @PathVariable("playerName") String playerName) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setPlayerName(playerName);
        validatorService.validatePlayer(transactionDto);
        return transactionService.getTransactions(playerName);
    }


}
