package com.example.leovegas.service;

import com.example.leovegas.dto.PlayerDto;
import com.example.leovegas.dto.ResponseMessage;
import com.example.leovegas.dto.WalletDto;
import com.example.leovegas.exception.AlreadyExistsException;
import com.example.leovegas.exception.IsNotExistException;
import com.example.leovegas.model.Player;
import com.example.leovegas.model.Wallet;
import com.example.leovegas.repository.PlayerRepository;
import com.example.leovegas.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    Logger logger = LoggerFactory.getLogger(PlayerService.class);
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private WalletRepository walletRepository;

    public PlayerDto getPlayerByName(String playerName) throws IsNotExistException {
        Player currentPlayer = playerRepository.findPlayerByName(playerName);
        logger.debug("Player status: " + currentPlayer.getPlayerName());
        if (currentPlayer == null && currentPlayer.getPlayerName().equals("")) {
            logger.info("*** No such player named in the database ***");
            throw new IsNotExistException("The player does not exist!");
        }
        Player player = playerRepository.findPlayerByName(playerName);
        return playerMapper(player);
    }

    public List<PlayerDto> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream().map(this::playerMapper).collect(Collectors.toList());
    }

    @Transactional
    public ResponseMessage createNewPlayer(PlayerDto playerDTO) throws AlreadyExistsException {
        ResponseMessage responseMessage = new ResponseMessage();
        Player currentPlayer = playerRepository.findPlayerByName(playerDTO.getPlayerName());
        logger.debug("Player name- " + currentPlayer.getPlayerName());
        if (currentPlayer != null && currentPlayer.getPlayerName().isEmpty())
            throw new AlreadyExistsException("Player already exists!");

        Wallet wallet = new Wallet();
        wallet.setBalance(playerDTO.getWalletDTO().getBalance());
        wallet.setCurrencyCode(playerDTO.getWalletDTO().getCurrencyCode());
        walletRepository.save(wallet);

        Player player = new Player();
        player.setPlayerName(playerDTO.getPlayerName());
        player.setWallet(wallet);
        player.setCreatedAt(new Date(System.currentTimeMillis()));
        player = playerRepository.save(player);
        responseMessage.setPlayer(player);
        responseMessage.setMessage(HttpStatus.CREATED.name());
        return responseMessage;
    }

    private PlayerDto playerMapper(Player p) {
        PlayerDto playerDTO = new PlayerDto();
        playerDTO.setPlayerName(p.getPlayerName());
        playerDTO.setCreatedAt(p.getCreatedAt());

        WalletDto walletDTO = new WalletDto();
        walletDTO.setBalance(p.getWallet().getBalance());
        walletDTO.setCurrencyCode(p.getWallet().getCurrencyCode());

        playerDTO.setWalletDTO(walletDTO);
        return playerDTO;
    }

}
