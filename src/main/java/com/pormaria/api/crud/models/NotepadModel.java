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

    @Column(name = "notes", nullable = false, length = 999)
    private String notes;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = true)
    private EventModel event;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "writer_user_id", referencedColumnName = "id", nullable = false)
    private UserModel writeUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_modifier_user_id", referencedColumnName = "id")
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
