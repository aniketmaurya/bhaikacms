package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Crew;
import com.coviam.metadata.repository.CrewRepository;
import com.coviam.metadata.services.CrewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Page<Crew> getAllCrew(Integer pageNumber, Integer pageSize) {
        return crewRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Boolean deleteCrew(String role, String name) {
        return null;
    }
}
