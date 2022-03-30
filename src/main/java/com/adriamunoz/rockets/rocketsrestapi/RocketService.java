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


    public RocketService(RocketRepository rocketRepository, PropellerRepository propellerRepository) {
        this.rocketRepository = rocketRepository;
        this.propellerRepository = propellerRepository;

    }
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

    public Propeller findPropeller(String propellerId) throws Exception {
        return propellerRepository.findById(propellerId).get();
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
        propellerRepository.deleteAllByRocket(rocket);
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

    public Propeller updatePropellerMaxPower(String propellerId, Propeller data) throws Exception {
        Propeller propeller = findPropeller(propellerId);
        propeller.setMaxPower(data.getMaxPower());
        propellerRepository.save(propeller);
        return propeller;

    }

    public Propeller createPropeller(String rocketId,Propeller propellerToCreate) throws Exception {
        Rocket rocket = findRocket(rocketId);
        this.rocketRepository.save(rocket);
        this.propellerRepository.save(propellerToCreate);
        return propellerToCreate;

    }

    public Rocket move(String rocketId, Movement movement) throws Exception {
        Rocket rocket = findRocket(rocketId);

        for (int i = 0; i < movement.getTimes(); i++) {
            for (Propeller propeller : rocket.getPropellers()) {
                if (movement.getType() == Movement.ACCELERATE) propeller.increasePower();
                else if (movement.getType() == Movement.DECELERATE) propeller.decreasePower();
                propellerRepository.save(propeller);
            }
        }
        return rocketRepository.save(rocket);
    }


}
