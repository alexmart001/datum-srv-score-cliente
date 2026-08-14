package br.com.datum.score.dto;

public record ScoreResponse(String cpf, int score, Classification classification) {
}
