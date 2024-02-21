package com.pormaria.api.crud.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "events")
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_title", nullable = false)
    private String eventTitle;

    @Column(name = "event_subtitle")
    private String eventSubtitle;

    @Column(name = "event_description", nullable = false)
    private String eventDescription;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "event_time_from")
    private String eventTimeFrom;

    @Column(name = "event_time_to")
    private String eventTimeTo;

    @Column(name = "map_location")
    private String mapLocation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "event_type_id", referencedColumnName = "id")
    private EventTypeModel eventType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotepadModel> notes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel creatorUser;

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventSubtitle() {
        return eventSubtitle;
    }

    public void setEventSubtitle(String eventSubtitle) {
        this.eventSubtitle = eventSubtitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTimeFrom() {
        return eventTimeFrom;
    }

    public void setEventTimeFrom(String eventTimeFrom) {
        this.eventTimeFrom = eventTimeFrom;
    }

    public String getEventTimeTo() {
        return eventTimeTo;
    }

    public void setEventTimeTo(String eventTimeTo) {
        this.eventTimeTo = eventTimeTo;
    }

    public String getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public EventTypeModel getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeModel eventType) {
        this.eventType = eventType;
    }

    public List<NotepadModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NotepadModel> notes) {
        this.notes = notes;
    }

    public UserModel getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(UserModel creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("eventTitle", eventTitle)
                .append("eventSubtitle", eventSubtitle)
                .append("eventDescription", eventDescription)
                .append("creationDate", creationDate)
                .append("eventDate", eventDate)
                .append("eventTimeFrom", eventTimeFrom)
                .append("eventTimeTo", eventTimeTo)
                .append("mapLocation", mapLocation)
                .append("eventType", eventType)
                .append("notes", notes)
                .append("creatorUser", creatorUser)
                .toString();
    }
}
