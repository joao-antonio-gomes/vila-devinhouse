package br.com.village;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class tests {
    public static void main(String[] args) {

        String date = "04/12/1996";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(date, format);
        System.out.println(birthDate);
    }
}
