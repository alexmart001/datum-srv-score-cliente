package br.com.datum.score.controller;

import br.com.datum.score.dto.ScoreResponse;
import br.com.datum.score.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/scores/{cpf}")
    public ResponseEntity<ScoreResponse> buscarScore(@PathVariable String cpf) {
        return ResponseEntity.ok(scoreService.consultarScore(cpf));
    }
}
