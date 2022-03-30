package com.adriamunoz.rockets.rocketsrestapi;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "rockets")

public class Rocket {

    @Id
    private String id = UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    public List<Propeller> getPropellers() {
        return propellers;
    }

    private void checkCode(String code) throws Exception {
        if (code.length() != 8) throw new Exception();
    }


}