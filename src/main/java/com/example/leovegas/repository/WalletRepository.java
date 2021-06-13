package com.example.leovegas.repository;

import com.example.leovegas.model.Player;
import com.example.leovegas.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByPlayer(Player player);
}
