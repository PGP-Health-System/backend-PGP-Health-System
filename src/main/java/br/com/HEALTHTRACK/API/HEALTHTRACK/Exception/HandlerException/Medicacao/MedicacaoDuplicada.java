package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicacao;

public class MedicacaoDuplicada extends RuntimeException {
    public MedicacaoDuplicada(String message) {
        super(message);
    }
}
