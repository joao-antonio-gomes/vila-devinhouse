package br.com.village.model.transport;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VillagerDTO {
    private String firstName;
    private String surname;
    private Integer age;
    private Double rent;
    private Integer id;
    private String password;
    private Date birthDate;
    private String cpf;
    private Set<String> role = new HashSet<>();
    private String email;

    public VillagerDTO(String firstName, String surname, Integer age, String cpf, String password, Double rent, Date birthDate, String email, Set<String> role) {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.rent = rent;
        this.password = pe.encode(password);
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.role = role;
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
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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
