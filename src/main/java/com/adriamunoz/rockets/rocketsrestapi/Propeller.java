package com.adriamunoz.rockets.rocketsrestapi;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Propeller {

    @Id
    @GeneratedValue
    private Long id;
    private int currentPower = 0;
    private int maxPower;
    private static final int POWER_STEP = 10;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "rocket_id")
    private Rocket rocket;

    public Propeller() {

    }

    public Propeller(int maxPower) throws Exception {
        checkMaxPower(maxPower);
        this.maxPower = maxPower;

    }

    private void checkMaxPower(int maxPower) throws Exception {
        if (maxPower <= 0) throw new Exception("Potència no pot ser 0");
    }

    public void setCurrentPower(int currentPower) {
        this.currentPower = currentPower;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public int getCurrentPower() {
        return currentPower;
    }

    public void setMaxPower(int maxPower) throws Exception {
        checkMaxPower(maxPower);
        this.maxPower = maxPower;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Long getId() {
        return id;
    }

    public void increasePower() {
        currentPower += POWER_STEP;
        if (currentPower > maxPower) {
            currentPower = maxPower;
        }
    }

    public void decreasePower() {
        currentPower -= POWER_STEP;
        if (currentPower < 0) {
            currentPower = 0;
        }
    }

}