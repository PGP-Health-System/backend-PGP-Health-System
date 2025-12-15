package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente;

public class CpfDuplicado extends RuntimeException {
    public CpfDuplicado(String message) {
        super(message);
    }
}
