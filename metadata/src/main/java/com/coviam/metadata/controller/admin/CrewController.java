package com.coviam.metadata.controller.admin;

import com.coviam.metadata.entity.Crew;
import com.coviam.metadata.services.CrewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CrewController {

    @Autowired
    private CrewServices crewServices;

    @PutMapping("/addCrew")
    public ResponseEntity<Boolean> addCrew(@RequestBody List<Crew> crewList) {
        return ResponseEntity.ok(crewServices.addCrew(crewList));
    }


    public ResponseEntity<Page<Crew>> getAllCrewRoles(Integer pageNumber, Integer pageSize) {
        return ResponseEntity.ok(crewServices.getAllCrew(pageNumber, pageSize));
    }

    //delete crew
    //update crew

}
