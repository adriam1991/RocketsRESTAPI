package com.adriamunoz.rockets.rocketsrestapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RocketRestController {
    private RocketService rocketService;

    public RocketRestController(RocketService rocketService) {
        this.rocketService = rocketService;
    }

    @PostMapping("/rockets")
    public Rocket createRocket(@RequestBody Rocket rocketToCreate) {
        return rocketService.createRocket(rocketToCreate);
    }

    @PostMapping("/rockets/{rocketId}/movement")
    public Rocket accelerateOrDecelerateRocket(@PathVariable String rocketId, @RequestBody Movement movement) throws Exception {
        return rocketService.move(rocketId, movement);
    }

   @PostMapping("/rockets/{rocketId}/propellers")
  public Propeller createPropeller(@PathVariable String rocketId, @RequestBody Propeller propellerToCreate) throws Exception {
     return rocketService.createPropeller(rocketId,propellerToCreate);
 }

 @PutMapping("/rockets/{rocketId}/propellers/{propellerId}")
 public void updatePropellerMaxPower(@PathVariable String propellerId,@RequestBody Propeller propellerToUpdate) throws Exception {
     rocketService.updatePropellerMaxPower(propellerId, propellerToUpdate);
 }


    @PutMapping("/rockets/{rocketId}")
    public void updateRocket(@PathVariable String rocketId, @RequestBody Rocket rocketToUpdate) throws Exception {
        rocketService.updateRocket(rocketId, rocketToUpdate);
    }


    @GetMapping("/rockets")
    public List<Rocket> getRockets() {
        return rocketService.getRockets();
    }

    @GetMapping("/rockets/{rocketId}")
    public Rocket getRocket(@PathVariable String rocketId) throws Exception {
        return rocketService.findRocket(rocketId);
    }

    @GetMapping("/rockets/{rocketId}/propellers")
    public List<Propeller> getPropellers(@PathVariable String rocketId) throws Exception {
        return rocketService.getPropellers(rocketId);
    }

    @GetMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public Propeller getPropeller(@PathVariable String rocketId, @PathVariable String propellerId) throws Exception {
        return rocketService.getPropeller(rocketId, propellerId);
    }

    @DeleteMapping("/rockets")
    public void removeAllRockets() {
        rocketService.removeAllRockets();
    }

    @DeleteMapping("/rockets/{rocketId}")
    public void removeRocket(@PathVariable String rocketId) {
        rocketService.removeRocket(rocketId);
    }

    @DeleteMapping("/rockets/{rocketId}/propellers")
    public void removeAllPropellers(@PathVariable String rocketId) throws Exception {
        rocketService.removeAllPropellers(rocketId);
    }


    @DeleteMapping("/rockets/{rocketId}/propellers/{propellerId}")
    public void removePropeller(@PathVariable String rocketId, @PathVariable String propellerId) throws Exception {
        rocketService.removePropeller(rocketId, rocketId);

    }


}
