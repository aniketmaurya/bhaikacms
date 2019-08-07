package com.coviam.metadata.controller.admin;

import com.coviam.metadata.entity.Crew;
import com.coviam.metadata.services.CrewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@CrossOrigin
public class CrewController {

    @Autowired
    private CrewServices crewServices;

    @PutMapping("/addCrew")
    public ResponseEntity<Boolean> addCrew(@RequestBody List<Crew> crewList) {
        return ResponseEntity.ok(crewServices.addCrew(crewList));
    }


    @GetMapping("/getAllCrewRoles")
    public ResponseEntity<List<Crew>> getAllCrewRoles() {
        return ResponseEntity.ok(crewServices.getAllCrew());
    }

    @DeleteMapping("/deleteCrewRole")
    public ResponseEntity<Boolean> deleteCrewRole(@RequestParam(name = "crewId") String crewId) {
        return ResponseEntity.ok(crewServices.deleteCrew(crewId));
    }

    //update crew
    @PutMapping("/updateCrewRole")
    public ResponseEntity<Boolean> updateCrewRole(@RequestParam(name = "id") String crewId, @RequestParam(name = "role") String role) {
        return ResponseEntity.ok(crewServices.updateCrew(crewId, role));
    }

}
