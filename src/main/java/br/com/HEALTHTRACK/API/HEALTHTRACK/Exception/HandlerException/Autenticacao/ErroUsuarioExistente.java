package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Autenticacao;

public class ErroUsuarioExistente extends RuntimeException {
    public ErroUsuarioExistente(String message) {
        super(message);
    }
}
