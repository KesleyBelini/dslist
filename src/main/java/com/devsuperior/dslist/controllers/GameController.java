package com.devsuperior.dslist.controllers;

import java.util.List;

import com.devsuperior.dslist.dto.GameDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/games")
@Tag(name = "Games", description = "API para gerenciamento de jogos")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os jogos", description = "Retorna uma lista com todos os jogos disponíveis")
    public List<GameMinDTO> findAll() {
        return gameService.findAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar jogo por ID", description = "Retorna os detalhes completos de um jogo específico")
    public GameDTO findById(@PathVariable Long id) {
        return gameService.findById(id);
    }

}
