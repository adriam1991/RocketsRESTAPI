package com.adriamunoz.rockets.rocketsrestapi;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Rocket {

    @Id
    @GeneratedValue
    private Long id;
    private String code;


    @OneToMany(mappedBy = "rocket")
    @JsonManagedReference
    private List<Propeller> propellers = new ArrayList<>();

    public Rocket() {
    }

    public Rocket(String code) throws Exception {
        checkCode(code);
        this.code = code;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    public void acceleratePropellers() {
        for (Propeller propeller : propellers) {
            propeller.increasePower();
        }
    }

    public void deceleratePropellers() {
        for (Propeller propeller : propellers) {
            propeller.decreasePower();
        }
    }

    public List<Propeller> getPropellers() {
        return propellers;
    }

    private void checkCode(String code) throws Exception {
        if (code.length() != 8) throw new Exception();
    }

    @JsonProperty("currentPower")
    public int currentPower() {
        return propellers.stream()
                .mapToInt(Propeller::getCurrentPower)
                .sum();
    }


}

