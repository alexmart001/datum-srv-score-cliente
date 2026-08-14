package br.com.datum.score.validation;

import java.util.regex.Pattern;

/**
 * Validador de CPF (Cadastro de Pessoas Físicas).
 * <p>
 * Verifica se a string possui 11 dígitos numéricos, descarta sequências de
 * dígitos repetidos (ex.: "00000000000", "11111111111") e confere os dois
 * dígitos verificadores segundo o algoritmo oficial da Receita Federal.
 */
public final class CpfValidator {

    private static final Pattern APENAS_DIGITOS = Pattern.compile("\\d{11}");
    private static final Pattern DIGITOS_REPETIDOS = Pattern.compile("(\\d)\\1{10}");

    private CpfValidator() {
    }

    public static boolean isValid(String cpf) {
        if (cpf == null || !APENAS_DIGITOS.matcher(cpf).matches()) {
            return false;
        }
        if (DIGITOS_REPETIDOS.matcher(cpf).matches()) {
            return false;
        }

        int[] digitos = cpf.chars().map(c -> c - '0').toArray();

        int primeiroDv = calcularDigitoVerificador(digitos, 9);
        if (primeiroDv != digitos[9]) {
            return false;
        }

        int segundoDv = calcularDigitoVerificador(digitos, 10);
        return segundoDv == digitos[10];
    }

    private static int calcularDigitoVerificador(int[] digitos, int quantidadeBase) {
        int peso = quantidadeBase + 1;
        int soma = 0;
        for (int i = 0; i < quantidadeBase; i++) {
            soma += digitos[i] * peso;
            peso--;
        }

        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}
