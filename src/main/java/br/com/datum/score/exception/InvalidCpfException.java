package br.com.datum.score.exception;

public class InvalidCpfException extends RuntimeException {

    public InvalidCpfException(String cpf) {
        super("CPF inválido: " + cpf);
    }
}
