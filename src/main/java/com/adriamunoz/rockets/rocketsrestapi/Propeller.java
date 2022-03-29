package com.adriamunoz.rockets.rocketsrestapi;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "propellers")
public class Propeller {

    @Id
    private String propellerId = UUID.randomUUID().toString();
    private int currentPower;
    private int maxPower;

    @ManyToOne
    @JoinColumn(name = "rocket_id")
    private Rocket rocket;

    public Propeller() {

    }

    public Propeller(Rocket rocket) {
        this.currentPower = 0;
        this.rocket = rocket;
    }

    public void setCurrentPower(int currentPower) {
        this.currentPower = currentPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
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

    public int getCurrentPower() {
        return currentPower;
    }


}