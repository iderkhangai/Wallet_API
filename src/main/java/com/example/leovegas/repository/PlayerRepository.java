package com.example.leovegas.repository;

import com.example.leovegas.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT * FROM PLAYER WHERE PLAYER_NAME = ?1", nativeQuery = true)
    public Player findPlayerByName(String playerName);


}
