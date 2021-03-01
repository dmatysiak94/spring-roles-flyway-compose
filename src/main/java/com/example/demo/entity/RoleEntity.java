package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    public RoleEntity(String name) {
        this.name = name;
    }

    public RoleEntity() {
    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
