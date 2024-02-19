package com.pormaria.api.crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "person_contact")
public class PersonContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_type_id")
    private PersonContactTypeModel contactType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private PersonModel person;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "additional_value")
    private String additionalValue;

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PersonContactTypeModel getContactType() {
        return contactType;
    }

    public void setContactType(PersonContactTypeModel contactType) {
        this.contactType = contactType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(String additionalValue) {
        this.additionalValue = additionalValue;
    }
}
