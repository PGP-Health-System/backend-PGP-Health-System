package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Medicamento;

public class MedicamentoNaoLocalizado extends RuntimeException {
    public MedicamentoNaoLocalizado(String message) {
        super(message);
    }
}
