package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente;

public class EmailDuplicado extends RuntimeException {
    public EmailDuplicado(String message) {
        super(message);
    }
}
