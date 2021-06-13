package com.example.leovegas.service;

import com.example.leovegas.dto.TransactionDto;
import com.example.leovegas.dto.WalletDto;
import com.example.leovegas.exception.IsNotExistException;
import com.example.leovegas.exception.WalletDoesntExistException;
import com.example.leovegas.model.Player;
import com.example.leovegas.model.Transaction;
import com.example.leovegas.model.Wallet;
import com.example.leovegas.repository.PlayerRepository;
import com.example.leovegas.repository.TransactionRepository;
import com.example.leovegas.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public WalletDto getWallet(String playerName) throws IsNotExistException, WalletDoesntExistException {
        Optional<Player> player = Optional.ofNullable(playerRepository.findPlayerByName(playerName));
        if (player.isEmpty())
            throw new IsNotExistException("The player does not exist!");
        Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findByPlayer(player.get()));
        if (wallet.isEmpty())
            throw new WalletDoesntExistException("The wallet does not exist!");

        return mapWalletDTO(wallet.get());
    }


    @Transactional
    public WalletDto makeCredit(TransactionDto transactionDTO) {

        Player player = playerRepository.findPlayerByName(transactionDTO.getPlayerName());
        Wallet wallet = player.getWallet();
        wallet.setBalance(wallet.getBalance() + Double.parseDouble(transactionDTO.getTransactionAmount()));
        wallet = walletRepository.save(wallet);
        transactionDTO.setTransactionType("CREDIT");
        createTransactionHistory(transactionDTO, player);

        return mapWalletDTO(wallet);
    }


    @Transactional
    public WalletDto makeDebit(TransactionDto transactionDTO) {

        Player player = playerRepository.findPlayerByName(transactionDTO.getPlayerName());
        Wallet wallet = player.getWallet();
        wallet.setBalance(wallet.getBalance() - Double.parseDouble(transactionDTO.getTransactionAmount()));
        wallet = walletRepository.save(wallet);
        transactionDTO.setTransactionType("DEBIT");
        createTransactionHistory(transactionDTO, player);
        return mapWalletDTO(wallet);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createTransactionHistory(TransactionDto transactionDTO, Player player) {

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionDTO.getTransactionId());
        transaction.setAmount(Long.parseLong(transactionDTO.getTransactionAmount()));
        transaction.setCurrencyCode(player.getWallet().getCurrencyCode());
        transaction.setTransactionDate(new Date(System.currentTimeMillis()));
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction = transactionRepository.save(transaction);
        transaction.setPlayer(player);
        transactionRepository.save(transaction);
    }

    private WalletDto mapWalletDTO(Wallet wallet) {
        WalletDto walletDTO = new WalletDto();
        walletDTO.setBalance(wallet.getBalance());
        walletDTO.setCurrencyCode(wallet.getCurrencyCode());
        return walletDTO;
    }
}
