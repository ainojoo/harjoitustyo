package fi.ainojoro.harjoitustyo.domain;


import java.time.LocalDate;
import java.util.List;

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
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "käyttäjät")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Käyttäjä nimi puuttuu")
    @Column (unique = true)
    private String username;

    private String passwordHash;
    
    @NotBlank(message = "Anna nimi")
    private String name;

    @Email(message = "Virheellinen sähköposti osoite")
    private String email;

    @Size(max=20, message = "Puhelinnumero on liian pitkä")
    private String phone;

    @Past(message = "Syntymäpäivä ei voi olla tulevaisuudessa")
    private LocalDate bday;
    


    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;
    
    @OneToMany(mappedBy = "participant")
    private List<EventParticipant> participations;


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

    

}
