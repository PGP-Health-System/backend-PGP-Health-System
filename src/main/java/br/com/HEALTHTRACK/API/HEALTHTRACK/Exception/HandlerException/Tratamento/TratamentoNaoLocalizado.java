package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Tratamento;

public class TratamentoNaoLocalizado extends RuntimeException {
    public TratamentoNaoLocalizado(String message) {
        super(message);
    }
}
