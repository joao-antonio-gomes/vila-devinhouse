package br.com.village.util;

import br.com.village.exceptions.CpfException;
import br.com.village.exceptions.ResidentsException;

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

    public static String validaSenha(String senha) throws ResidentsException {
        if (senha.length() < 8) {
            throw new ResidentsException("Senha muito curta, deve ter no mínimo 8 caracteres");
        }

        boolean hasNumbers = false;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasEspecialCharacter = false;

        for (int i = 0; i < senha.length(); i++) {
            if (Character.isDigit(senha.charAt(i))) {
                hasNumbers = true;
            }
            if (Character.isUpperCase(senha.charAt(i))) {
                hasUppercase = true;
            }
            if (Character.isLowerCase(senha.charAt(i))) {
                hasLowercase = true;
            }
            if (!Character.isLetterOrDigit(senha.charAt(i))) {
                hasEspecialCharacter = true;
            }
        }

        if (!hasNumbers) {
            throw new ResidentsException("Senha deve conter pelo menos um número");
        }

        if (!hasUppercase) {
            throw new ResidentsException("Senha deve conter pelo menos uma letra maiúscula");
        }

        if (!hasLowercase) {
            throw new ResidentsException("Senha deve conter pelo menos uma letra minúscula");
        }

        if (!hasEspecialCharacter) {
            throw new ResidentsException("Senha deve conter pelo menos um caractere especial");
        }

        return senha;
    }
}
