package com.handtruth.bot.time.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

@Entity
@Table(name = "users")
public class Users {

    @Id
    private Long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "user")
    private Dtask dtask;

    public Users() {
    }

    public Users(int id, String name) {
        this.id = (long) id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dtask getDtask() {
        return dtask;
    }

    public void setDtask(Dtask dtask) {
        this.dtask = dtask;
    }
}
