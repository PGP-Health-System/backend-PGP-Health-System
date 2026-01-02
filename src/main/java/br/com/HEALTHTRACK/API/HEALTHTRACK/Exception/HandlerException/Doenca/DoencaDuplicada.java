package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca;

public class DoencaDuplicada extends RuntimeException {
    public DoencaDuplicada(String message) {
        super(message);
    }
}
