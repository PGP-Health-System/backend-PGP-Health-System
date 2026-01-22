package br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.SintomaEnum;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Sintoma.SintomaInvalidoException;

public enum GravidadeSintoma {
    LEVE("L"),
    MODERADO("M"),
    GRAVE("G"),
    MUITO_GRAVE("MG");

    private String codigo;

    GravidadeSintoma(String codigo) {
        this.codigo = codigo;
    }

    GravidadeSintoma() {
    }


    public GravidadeSintoma diminuir() {
        if (LEVE == this){
            throw new SintomaInvalidoException("Sintoma já está com a gravidade mais baixa possivel!");
        }
        return switch(this) {
            case MUITO_GRAVE -> GRAVE;
            case GRAVE -> MODERADO;
            case MODERADO -> LEVE;
            default -> throw new IllegalStateException(STR."Gravidade desconhecida: \{this}");
        };
    }

    public GravidadeSintoma aumentar() {
        if (MUITO_GRAVE == this){
            throw new SintomaInvalidoException("Sintoma já está com a gravidade mais alta possivel!");
        }
        return switch(this) {
            case LEVE -> MODERADO;
            case MODERADO -> GRAVE;
            case GRAVE -> MUITO_GRAVE;
            default -> throw new IllegalStateException(STR."Gravidade desconhecida: \{this}");
        };
    }



}
