package com.pormaria.api.crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notepad")
public class NotepadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "notes", columnDefinition = "TEXT", nullable = false)
    private String notes;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private EventModel event;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_user_id", nullable = false)
    private UserModel writeUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_modifier_user_id")
    private UserModel lastModifierUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public UserModel getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(UserModel writeUser) {
        this.writeUser = writeUser;
    }

    public UserModel getLastModifierUser() {
        return lastModifierUser;
    }

    public void setLastModifierUser(UserModel lastModifierUser) {
        this.lastModifierUser = lastModifierUser;
    }

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }
}
