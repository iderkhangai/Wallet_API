package com.example.leovegas.repository;

import com.example.leovegas.model.Player;
import com.example.leovegas.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findTransactionsByPlayer(Player player);

    @Query(value = "SELECT count(1) FROM TRANSACTIONS WHERE TRANSACTION_ID= ?1", nativeQuery = true)
    public Integer findTransactionBy(String transactionId);
}
