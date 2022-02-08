package com.labs.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dots")
public class Dot {
    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private double x;
    private double y;
    private double r;
    private String curTime;
    private double execTime;
    private boolean result;


    public Dot(String login, double x, double y, double r, String curTime, double execTime, boolean result) {
        this.login = login;
        this.x = x;
        this.y = y;
        this.r = r;
        this.curTime = curTime;
        this.execTime = execTime;
        this.result = result;
    }
}
