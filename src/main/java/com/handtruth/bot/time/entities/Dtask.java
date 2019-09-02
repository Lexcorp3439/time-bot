package com.handtruth.bot.time.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "dtask")
public class Dtask {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = -1;

    @Column(name = "monday")
    private boolean monday = false;

    @Column(name = "tuesday")
    private boolean tuesday = false;

    @Column(name = "wednesday")
    private boolean wednesday = false;

    @Column(name = "thursday")
    private boolean thursday = false;

    @Column(name = "friday")
    private boolean friday = false;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private Users user;

    public Dtask() {
    }

    public Dtask(Users user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public void setMask(Dtask dtaskK) {
        monday = dtaskK.monday;
        tuesday = dtaskK.tuesday;
        wednesday = dtaskK.wednesday;
        thursday = dtaskK.thursday;
        friday = dtaskK.friday;
    }

    public void clear() {
        monday = false;
        tuesday = false;
        wednesday = false;
        thursday = false;
        friday = false;
    }

}
