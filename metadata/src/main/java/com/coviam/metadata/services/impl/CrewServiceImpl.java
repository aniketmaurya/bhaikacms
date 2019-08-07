package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Crew;
import com.coviam.metadata.repository.CrewRepository;
import com.coviam.metadata.services.CrewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrewServiceImpl implements CrewServices {

    @Autowired
    private CrewRepository crewRepository;

    @Override
    public Boolean addCrew(List<Crew> crewList) {
        crewRepository.saveAll(crewList);
        return Boolean.TRUE;
    }

    @Override
    public List<Crew> getAllCrew() {
        List<Crew> crewList = new ArrayList<>();
        crewRepository.findAll().forEach(crewList::add);
        return crewList;
    }

    @Override
    public Boolean deleteCrew(String crewId) {
        crewRepository.deleteById(crewId);
        return Boolean.TRUE;
    }


    public Boolean updateCrew(String id, String role) {

        if (!crewRepository.existsById(id))
            return Boolean.FALSE;

        Crew crew = crewRepository.findById(id).get();
        crew.setRole(role);
        crewRepository.save(crew);
        return Boolean.TRUE;
    }
}
