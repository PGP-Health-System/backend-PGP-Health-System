package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Autenticacao;

public class ErroLogin extends RuntimeException {
    public ErroLogin(String message) {
        super(message);
    }
}
