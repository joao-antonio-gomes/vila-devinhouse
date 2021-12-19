package br.com.village.util;

import br.com.village.exceptions.CpfException;

public class Validadores {
    public static String validaCpf(String cpf) throws CpfException {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new CpfException("CPF inválido");
        }

        int d1 = 0;
        int d2 = 0;
        int digito1 = 0;
        int digito2 = 0;
        int resto = 0;
        int digitoCPF = 0;
        String nDigResult;
        String nDigVerific;

        d1 = Integer.parseInt(cpf.substring(0, 1));
        d2 = Integer.parseInt(cpf.substring(1, 2));
        digito1 = Integer.parseInt(cpf.substring(2, 3));
        digito2 = Integer.parseInt(cpf.substring(3, 4));
        resto = (d1 * 100) + (d2 * 10);
        digitoCPF = 11 - (resto % 11);

        if (digitoCPF > 9) {
            digitoCPF = 0;
        }

        if (digitoCPF != digito1) {
            throw new CpfException("CPF inválido");
        }

        d1 = Integer.parseInt(cpf.substring(0, 1));
        d2 = Integer.parseInt(cpf.substring(1, 2));
        digito1 = Integer.parseInt(cpf.substring(2, 3));
        digito2 = Integer.parseInt(cpf.substring(3, 4));
        resto = (d1 * 10) + (d2 * 9);
        digitoCPF = 11 - (resto % 11);

        if (digitoCPF > 9) {
            digitoCPF = 0;
        }

        if (digitoCPF != digito2) {
            throw new CpfException("CPF inválido");
        }

        return Formatadores.formataCpf(cpf);
    }
}
