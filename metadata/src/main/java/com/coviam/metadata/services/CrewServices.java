package com.coviam.metadata.services;

import com.coviam.metadata.entity.Crew;

import java.util.List;

public interface CrewServices {

    Boolean addCrew(List<Crew> crewList);
    Boolean deleteCrew(String role, String name);
}
