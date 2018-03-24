package com.jnj.atm.services.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="atm")
public class Atm implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="note_5")
    private int note_5;
    @Column(name="note_10")
    private int note_10;
    @Column(name="note_20")
    private int note_20;
    @Column(name="note_50")
    private int note_50;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote_5() {
        return note_5;
    }

    public void setNote_5(int note_5) {
        this.note_5 = note_5;
    }

    public int getNote_10() {
        return note_10;
    }

    public void setNote_10(int note_10) {
        this.note_10 = note_10;
    }

    public int getNote_20() {
        return note_20;
    }

    public void setNote_20(int note_20) {
        this.note_20 = note_20;
    }

    public int getNote_50() {
        return note_50;
    }

    public void setNote_50(int note_50) {
        this.note_50 = note_50;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "id=" + id +
                ", note_5=" + note_5 +
                ", note_10=" + note_10 +
                ", note_20=" + note_20 +
                ", note_50=" + note_50 +
                '}';
    }
}
