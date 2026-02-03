package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.MedicacaoPaciente;

public class PacienteMedicacaoNaoEncontrado extends RuntimeException {
    public PacienteMedicacaoNaoEncontrado(String message) {
        super(message);
    }
}
