package com.example.leovegas.controller;

import com.example.leovegas.model.Player;
import com.example.leovegas.repository.PlayerRepository;
import com.example.leovegas.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlayerControllerTest {

    @MockBean
    PlayerRepository playerRepository;
    @Mock
    PlayerService playerService;
    @MockBean
    private PlayerController playerController;


    @Test
    @Order(1)
    void createPlayer() {
//        Player p1 = new Player();
//        p1.setPlayerId(424L);
//        p1.setPlayerName("ider");
//        playerRepository.save(p1);
//        assertNotNull(playerRepository.findById(424L).get());
    }

    @Test
    @Order(2)
    void getALLPlayer() throws Exception {
        List<Player> list = playerRepository.findAll();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    void getSinglePlayer() throws Exception {
        Player player = playerRepository.findById(424L).get();
        assertEquals("ider", player.getPlayerName());
    }
}