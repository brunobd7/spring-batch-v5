package br.com.dantas.springbatchv5.domain;

public enum TipoConta {

    PRATA, OURO, PLATINA, DIAMANTE, INVALIDA;

    public static TipoConta fromFaixaSalarial(Double faixaSalarial) {

        if (faixaSalarial == null)
            return INVALIDA;

        if (faixaSalarial <= 3000)
            return PRATA;
        else if (faixaSalarial > 3000 && faixaSalarial <= 5000)
            return OURO;
        else if (faixaSalarial > 5000 && faixaSalarial <= 10000)
            return PLATINA;
        else
            return DIAMANTE;
    }
}
