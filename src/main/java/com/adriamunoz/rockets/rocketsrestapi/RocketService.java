package com.adriamunoz.rockets.rocketsrestapi;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RocketService {

    private RocketRepository rocketRepository;
    private PropellerRepository propellerRepository;


    public Rocket createRocket(Rocket rocketToCreate) {
        this.rocketRepository.save(rocketToCreate);
        return rocketToCreate;
    }

    public List<Rocket> getRockets() {
        List<Rocket> rockets = new ArrayList<>();
        for (Rocket rocket : rocketRepository.findAll()
        ) {
            rockets.add(rocket);
        }
        return rockets;
    }

    public Rocket findRocket(String rocketId) throws Exception {
        return rocketRepository.findById(rocketId).get();
    }

    public List<Propeller> getPropellers(String rocketId) throws Exception {
        Rocket rocket = findRocket(rocketId);
        List<Propeller> propellers = rocket.getPropellers();
        return propellers;
    }

    public Propeller getPropeller(String rocketId, String propellerId) throws Exception {
        return propellerRepository.findById(propellerId).get();
    }

    public void removeAllRockets() {
        rocketRepository.deleteAll();
    }

    public void removeRocket(String rocketId) {
        rocketRepository.deleteById(rocketId);
    }

    public void removeAllPropellers(String rocketId) throws Exception {
        Rocket rocket = findRocket(rocketId);
        propellerRepository.deleteAllByRestaurant(rocket);
    }

    public void removePropeller(String rocketId, String propellerId) throws Exception {
        Rocket rocket = findRocket(rocketId);
        rocketRepository.deleteById(propellerId);

    }

    public Rocket updateRocket(String rocketId, Rocket data) throws Exception {
        Rocket rocket = findRocket(rocketId);
        rocket.setCode(data.getCode());
        rocketRepository.save(rocket);
        return rocket;

    }

    public Rocket move(String rocketId, Movement movement) throws Exception {
        Rocket rocket = findRocket(rocketId);

        if (rocket.getPropellers().isEmpty()) throw new Exception("El coet no te propellers");

        for (int i = 0; i < movement.getMovementXTimes(); i++) {
            for (Propeller propeller : rocket.getPropellers()) {
                if (movement.getType() == Movement.ACCELERATE) propeller.increasePower();
                else if (movement.getType() == Movement.DECELERATE) propeller.decreasePower();
                propellerRepository.save(propeller);
            }
        }

        return rocketRepository.save(rocket);

    }

    private static void assignPowerRocket1(Rocket rocket) throws Exception {
        int[] powers = {10, 30, 80};
        for (int power : powers) {
            rocket.addPropeller(power);
        }
    }


}
