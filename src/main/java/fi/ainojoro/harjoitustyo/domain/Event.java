package fi.ainojoro.harjoitustyo.domain;


import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "tapahtumat")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tapahtuma_id")
    private Long id;

    @NotBlank(message = "Tapahtumalla on oltava nimi")
    @Column (name = "nimi")
    private String title;

    @Size(max = 500)
    @Column(name = "kuvaus")
    private String desc;

    @NotNull(message = "Tapahtumalla on oltava päivämäärä")
    @FutureOrPresent(message = "Päivämäärä ei voi olla menneisyydessä")
    @Column (name="pvm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @OneToMany(mappedBy = "event")
    private List<EventParticipant> participants;
    

    public Event() {
    }

    public Event(String title, String desc, LocalDate date) {
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<EventParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<EventParticipant> participants) {
        this.participants = participants;
    }


}
