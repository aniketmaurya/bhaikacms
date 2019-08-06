package com.coviam.metadata.services;

import com.coviam.metadata.entity.Crew;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CrewServices {

    Boolean addCrew(List<Crew> crewList);

    Page<Crew> getAllCrew(Integer pageNumber, Integer pageSize);

    Boolean deleteCrew(String role, String name);
}
