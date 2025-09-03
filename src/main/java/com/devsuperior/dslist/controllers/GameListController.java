package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
@Tag(name = "Game Lists", description = "API para gerenciamento de listas de jogos")
public class GameListController {

    private final GameListService gameListService;
    private final GameService gameService;

    public GameListController(GameListService gameListService, GameService gameService) {
        this.gameListService = gameListService;
        this.gameService = gameService;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar lista por ID", description = "Retorna uma lista de jogos específica")
    public GameListDTO findById(@PathVariable Long id) {
        return gameListService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Listar todas as listas", description = "Retorna todas as listas de jogos disponíveis")
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

    @GetMapping(value = "/{listId}/games")
    @Operation(summary = "Listar jogos de uma lista", description = "Retorna todos os jogos de uma lista específica")
    public List<GameMinDTO> findByList(@PathVariable Long listId) {
        return gameService.findByList(listId);
    }

    @PostMapping(value = "/{listId}/replacement")
    @Operation(summary = "Reordenar jogos na lista", description = "Move um jogo de uma posição para outra na lista")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}
