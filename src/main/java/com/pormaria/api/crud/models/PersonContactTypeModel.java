package com.pormaria.api.crud.models;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@Entity
@Table(name = "contact_type")
public class PersonContactTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_datetime")
    private Date creationDatetime;

    @Column(name = "is_social_media")
    private boolean isSocialMedia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Date creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public boolean isSocialMedia() {
        return isSocialMedia;
    }

    public void setSocialMedia(boolean socialMedia) {
        isSocialMedia = socialMedia;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("description", description)
                .append("creationDatetime", creationDatetime)
                .append("isSocialMedia", isSocialMedia)
                .toString();
    }
}
