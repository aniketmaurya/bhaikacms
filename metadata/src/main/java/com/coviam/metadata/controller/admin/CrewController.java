package com.coviam.metadata.controller.admin;

import com.coviam.metadata.entity.Crew;
import com.coviam.metadata.services.CrewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CrewController {

    @Autowired
    private CrewServices crewServices;

    @PutMapping("/addCrew")
    public ResponseEntity<Boolean> addCrew(@RequestBody List<Crew> crewList) {
        return ResponseEntity.ok(crewServices.addCrew(crewList));
    }

    @GetMapping("/getAllCrewRoles")
    public ResponseEntity<Page<Crew>> getAllCrewRoles(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        return ResponseEntity.ok(crewServices.getAllCrew(pageNumber,pageSize));
    }

    @DeleteMapping("/deleteCrewRole")
    public ResponseEntity<Boolean> deleteCrewRole(@RequestParam(name = "crewId") String crewId){
        return ResponseEntity.ok(crewServices.deleteCrew(crewId));
    }

    //update crew

}
