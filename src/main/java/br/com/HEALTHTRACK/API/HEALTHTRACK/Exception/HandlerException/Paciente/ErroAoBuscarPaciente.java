package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente;

public class ErroAoBuscarPaciente extends RuntimeException {
    public ErroAoBuscarPaciente(String message) {
        super(message);
    }
}
