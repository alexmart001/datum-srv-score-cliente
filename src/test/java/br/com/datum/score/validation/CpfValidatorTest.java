package br.com.datum.score.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CpfValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"11604567805", "52998224725"})
    void deveAceitarCpfComDigitosVerificadoresCorretos(String cpf) {
        assertTrue(CpfValidator.isValid(cpf));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "11604567800", // dígitos verificadores incorretos
            "1160456780",  // menos de 11 dígitos
            "116045678055", // mais de 11 dígitos
            "1160456780a", // caractere não numérico
            "00000000000", // todos os dígitos iguais
            "11111111111",
            "99999999999",
    })
    void deveRejeitarCpfInvalido(String cpf) {
        assertFalse(CpfValidator.isValid(cpf));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveRejeitarCpfNuloOuVazio(String cpf) {
        assertFalse(CpfValidator.isValid(cpf));
    }
}
