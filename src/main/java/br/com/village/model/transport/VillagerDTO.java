package br.com.village.model.transport;

import br.com.village.exceptions.CpfException;
import br.com.village.util.Validadores;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class VillagerDTO {
    private String firstName;
    private String surname;
    private Double rent;
    private Integer id;
    private String password;
    private LocalDate birthDate;
    private String cpf;
    private Set<String> role = new HashSet<>();
    private String email;

    public VillagerDTO() {}

    public VillagerDTO(Integer id, String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
        this.id = id;
    }

    public VillagerDTO(String firstName, String surname, String cpf, String password, Double rent, LocalDate birthDate, String email, Set<String> role) throws CpfException {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        this.firstName = firstName;
        this.surname = surname;
        this.rent = rent;
        this.password = pe.encode(password);
        this.birthDate = birthDate;
        this.cpf = Validadores.validaCpf(cpf);
        this.role = role;
        this.email = email;
    }

    public VillagerDTO(Integer id, String firstName, String surname, String cpf, String password, Double rent, String birthDate, String email, String role) throws ParseException {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.rent = rent;
        this.password = password;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthDate = LocalDate.parse(birthDate, format);
        this.cpf = cpf;
        this.role = Set.of(role);
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return this.birthDate.until(LocalDate.now()).getYears();
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
