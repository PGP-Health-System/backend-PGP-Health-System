package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Tratamento;

public class ErroAtivarMedicacao extends RuntimeException {
    public ErroAtivarMedicacao(String message) {
        super(message);
    }
}
