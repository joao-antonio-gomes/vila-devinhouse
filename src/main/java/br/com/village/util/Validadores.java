package br.com.village.util;

import br.com.village.exceptions.CpfException;

public class Validadores {
    public static String validaCpf(String cpf) throws CpfException {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new CpfException("CPF inválido");
        }

        int[] cpfArray = new int[11];
        for (int i = 0; i < cpf.length(); i++) {
            cpfArray[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += cpfArray[i] * (10 - i);
        }

        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }

        if (digito1 != cpfArray[9]) {
            throw new CpfException("CPF inválido");
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += cpfArray[i] * (11 - i);
        }

        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }

        if (digito2 != cpfArray[10]) {
            throw new CpfException("CPF inválido");
        }

        return Formatadores.formataCpf(cpf);
    }
}
