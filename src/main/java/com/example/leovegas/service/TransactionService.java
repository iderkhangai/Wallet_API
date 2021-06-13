package com.example.leovegas.service;

import com.example.leovegas.dto.TransactionDto;
import com.example.leovegas.model.Player;
import com.example.leovegas.model.Transaction;
import com.example.leovegas.repository.PlayerRepository;
import com.example.leovegas.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public List<TransactionDto> getTransactions(String playerName) {
        Player player = playerRepository.findPlayerByName(playerName);
        List<Transaction> transactionList = transactionRepository.findTransactionsByPlayer(player);
        return transactionList.stream()
                .map(this::TransactionMapper)
                .collect(Collectors.toList());
    }


    private TransactionDto TransactionMapper(Transaction transaction) {
        TransactionDto transactionDTO = new TransactionDto();
        transactionDTO.setTransactionId(transaction.getTransactionId());
        transactionDTO.setPlayerName(transaction.getPlayer().getPlayerName());
        transactionDTO.setTransactionAmount(transaction.getCurrencyCode() + " " + transaction.getAmount());
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        return transactionDTO;
    }
}
