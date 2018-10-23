package com.app.authspring.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "privileges", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Privileges implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privilegesId;
    private String name;
    private String description;

    public Privileges(){}

    public Long getPrivilegesId() {
        return privilegesId;
    }

    public void setPrivilegesId(Long privilegesId) {
        this.privilegesId = privilegesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
