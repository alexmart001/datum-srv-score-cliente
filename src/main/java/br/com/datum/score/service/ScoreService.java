package br.com.datum.score.service;

import br.com.datum.score.dto.Classification;
import br.com.datum.score.dto.ScoreResponse;
import br.com.datum.score.exception.InvalidCpfException;
import br.com.datum.score.validation.CpfValidator;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private static final int MOCK_SCORE = 750;
    private static final Classification MOCK_CLASSIFICATION = Classification.LOW_RISK;

    /**
     * Consulta o score do cliente pelo CPF informado.
     * <p>
     * Implementação atual é um MOCK: sempre retorna o mesmo score e
     * classificação, ecoando o CPF recebido na requisição.
     */
    public ScoreResponse consultarScore(String cpf) {
        if (!CpfValidator.isValid(cpf)) {
            throw new InvalidCpfException(cpf);
        }

        return new ScoreResponse(cpf, MOCK_SCORE, MOCK_CLASSIFICATION);
    }
}
