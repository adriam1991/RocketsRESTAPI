package com.adriamunoz.rockets.rocketsrestapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "rockets")

public class Rocket {

    @Id
    private String rocketId = UUID.randomUUID().toString();
    private String code;


    @OneToMany(mappedBy = "rocket")
    private List<Propeller> powerPropellants = new ArrayList<>();

    public Rocket() {

    }

    public Rocket(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    public String showPropellerStatus() {
        String result = "";
        int i = 1;
        for (Propeller propeller : powerPropellants) {
            result += "Propulsor " + i + " :" + propeller.getCurrentPower() + " potencia\n";
            i++;
        }
        return result;
    }

    public List<Propeller> getPropellers() {
        return powerPropellants;
    }

    private void checkCode(String code) throws Exception {
        if (code.length() != 8) throw new Exception();
    }

    public void addPropeller(int maxPower) throws Exception {
        Propeller p = createPropeller(maxPower);
        powerPropellants.add(p);
    }

    private Propeller createPropeller(int maxPower) throws Exception {
        Propeller propeller = new Propeller();
        propeller.setMaxPower(maxPower);
        return propeller;
    }

    public void accelerate() {
        for (Propeller propeller : powerPropellants) {
            propeller.increasePower();
        }
    }

    public void decelerate() {
        for (Propeller propeller : powerPropellants) {
            propeller.decreasePower();
        }
    }

    public static void calculateTotalpower(Rocket rocket) {
        List<Propeller> propellerList = rocket.powerPropellants;
        int totalPower = 0;

        for (Propeller propeller : propellerList) {
            totalPower = totalPower + propeller.getCurrentPower();
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }


}