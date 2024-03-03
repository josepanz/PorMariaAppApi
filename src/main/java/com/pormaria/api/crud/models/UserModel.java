package com.pormaria.api.crud.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "username")
    private String username;

    private boolean active;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "disabled_date")
    private Date disabledDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private PersonModel person;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.DETACH, orphanRemoval = false)
    private List<MembershipModel> membership;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("code", code)
                .append("username", username)
                .append("active", active)
                .append("creationDate", creationDate)
                .append("disabledDate", disabledDate)
                .append("person", person.toString())
                .append("password", password)
                .append("membership", membership)
                .toString();
    }

}
