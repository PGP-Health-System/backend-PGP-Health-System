package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente;

public class PacienteNaoLocalizado extends RuntimeException {
    public PacienteNaoLocalizado(String message) {
        super(message);
    }
}
