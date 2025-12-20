package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Paciente;

public class ErroCadastrarPaciente extends RuntimeException {
    public ErroCadastrarPaciente(String message) {
        super(message);
    }
}
