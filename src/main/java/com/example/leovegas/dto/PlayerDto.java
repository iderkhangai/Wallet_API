package com.example.leovegas.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@JsonRootName(value = "player")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDto {
    @NotEmpty(message = "Please enter player name!")
    private String playerName;
    private Date createdAt;
    @JsonProperty("wallet")
    private WalletDto walletDTO;
}
