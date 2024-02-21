package com.pormaria.api.crud.models;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "names")
    private String names;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "civil_status_id", referencedColumnName = "id")
    private CivilStatusModel civilStatus;

    @Column(name = "active")
    private boolean active;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "disabled_date")
    private Date disabledDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_type_id", referencedColumnName = "id")
    private PersonTypeModel personType;

    @Column(name = "last_names")
    private String lastNames;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonContactModel> personContact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public CivilStatusModel getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(CivilStatusModel civilStatus) {
        this.civilStatus = civilStatus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    public PersonTypeModel getPersonType() {
        return personType;
    }

    public void setPersonType(PersonTypeModel personType) {
        this.personType = personType;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public List<PersonContactModel> getPersonContact() {
        return personContact;
    }

    public void setPersonContact(List<PersonContactModel> personContact) {
        this.personContact = personContact;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("names", names)
                .append("civilStatus", civilStatus)
                .append("active", active)
                .append("birthdate", birthdate)
                .append("creationDate", creationDate)
                .append("disabledDate", disabledDate)
                .append("personType", personType)
                .append("lastNames", lastNames)
                .append("personContact", personContact.toString())
                .toString();
    }
}
