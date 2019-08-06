package com.coviam.metadata.services;

import com.coviam.metadata.entity.Crew;

import java.util.List;

public interface CrewServices {

    Boolean addCrew(List<Crew> crewList);

    List<Crew> getAllCrew();

    Boolean deleteCrew(String crewId);

    Boolean updateCrew(String id, String role);
}
