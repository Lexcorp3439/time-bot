package com.handtruth.bot.time.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dtasks")
public class Dtask {

    @Column(name = "id")
    private Integer id;

    @Column(name = "count")
    private int count;

    @Column(name = "name")
    private String name;

    public Dtask(Integer id, int count, String name) {
        this.id = id;
        this.count = count;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
