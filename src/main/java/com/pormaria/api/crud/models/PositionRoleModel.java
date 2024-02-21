package com.pormaria.api.crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "position_roles")
public class PositionRoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private PositionModel position;

    @OneToOne(fetch = FetchType.EAGER)
    private RoleModel role;

    private Date assignmentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
