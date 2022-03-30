package com.adriamunoz.rockets.rocketsrestapi;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "propellers")
public class Propeller {

    @Id
    private String id = UUID.randomUUID().toString();
    private int currentPower;
    private int maxPower;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "rocket_id")
    private Rocket rocket;

    public Propeller() {

    }

    public Propeller(int maxPower,Rocket rocket) throws Exception {
        checkMaxPower(maxPower);
        this.maxPower = maxPower;
        this.rocket = rocket;

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

    public String getId() {
        return id;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public void increasePower() {
        this.currentPower += 10;
        if (currentPower > maxPower) {
            currentPower = maxPower;
        }
    }

    public void decreasePower() {
        this.currentPower = currentPower - 10;
        if (currentPower <= 0) {
            currentPower = 0;
        }
    }

}