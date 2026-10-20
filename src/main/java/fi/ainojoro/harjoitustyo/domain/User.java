package fi.ainojoro.harjoitustyo.domain;


import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "kayttajat")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "kayttaja_id")
    private Long id;

    @NotBlank(message = "Käyttäjän nimi puuttuu")
    @Column (unique = true, name="kayttajanimi")
    private String username;

    @Column(name="salasana")
    private String passwordHash;
    
    @NotBlank(message = "Anna nimi")
    @Column(name="nimi")
    private String name;

    @Email(message = "Virheellinen sähköposti osoite")
    @Column(name="email")
    private String email;

    @Size(max=20, message = "Puhelinnumero on liian pitkä")
    @Column(name="puhelin")
    private String phone;

    @Past(message = "Syntymäpäivä ei voi olla tulevaisuudessa")
    @Column (name="syntymapaiva")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate bday;

    @ManyToOne
    @JoinColumn(name = "rooli_id")
    private Role role;
    
    @OneToMany(mappedBy = "participant")
    @JsonIgnore 
    private List<EventParticipant> participations;

    @NotNull 
    @Column (name = "paivita_salasana")
    private boolean mustChangePassword;


    public User() {
    }
   
    public User(String username, String passwordHash, String name, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public List<EventParticipant> getParticipations() {
        return participations;
    }
    public void setParticipations(List<EventParticipant> participations) {
        this.participations = participations;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", bday=" + bday
                + ", role=" + role + "]";
    }

    public LocalDate getBday() {
        return bday;
    }

    public void setBday(LocalDate bday) {
        this.bday = bday;
    }

    public boolean isMustChangePassword() {
        return mustChangePassword;
    }

    public void setMustChangePassword(boolean mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
    }

    

}
