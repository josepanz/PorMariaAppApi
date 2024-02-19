package com.pormaria.api.crud.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "positions")
public class PositionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String code;

    private String description;

    private boolean active;

    private Date creationDate;

    private Date disabledDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PositionRoleModel> positionRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public List<PositionRoleModel> getPositionRole() {
        return positionRole;
    }

    public void setPositionRole(List<PositionRoleModel> positionRole) {
        this.positionRole = positionRole;
    }
}
