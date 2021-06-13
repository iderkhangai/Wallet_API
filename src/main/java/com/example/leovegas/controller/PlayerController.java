package com.example.leovegas.controller;

import com.example.leovegas.dto.PlayerDto;
import com.example.leovegas.dto.ResponseMessage;
import com.example.leovegas.exception.AlreadyExistsException;
import com.example.leovegas.exception.IsNotExistException;
import com.example.leovegas.model.Player;
import com.example.leovegas.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/rest/v1")
public class PlayerController {
    private final static Logger logger = LoggerFactory.getLogger(PlayerController.class);
    @Autowired
    private PlayerService playerService;

    @GetMapping("/player/all")
    @ApiOperation(value = "Retrieve player's list")
    public ResponseEntity<List<PlayerDto>> getAllPlayer() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @PostMapping(value = "/player")
    @ApiOperation(value = "Create Player and Wallet")
    public ResponseEntity player(@Valid @RequestBody PlayerDto playerDTO) throws AlreadyExistsException {
        logger.debug("playerDTO - " + playerDTO);
        logger.info("playerDTO - " + playerDTO);
        ResponseMessage res = playerService.createNewPlayer(playerDTO);
        logger.info("createNewPlayer - " + res.getPlayer().getPlayerId());
        return new ResponseEntity(res, HttpStatus.OK);
    }


    @GetMapping(value = "/player/{playerName}")
    @ApiOperation(value = "Retrieve player's details")
    public ResponseEntity<PlayerDto> player(@PathVariable("playerName") final String playerName) throws IsNotExistException {

        return ResponseEntity.ok(playerService.getPlayerByName(playerName));
    }

}
