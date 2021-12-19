package br.com.village.util;

public class Formatadores {
    public static String formataCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
}
