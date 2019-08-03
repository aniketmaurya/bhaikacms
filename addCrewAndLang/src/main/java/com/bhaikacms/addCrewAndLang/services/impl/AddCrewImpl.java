package com.bhaikacms.addCrewAndLang.services.impl;

import com.bhaikacms.addCrewAndLang.dto.CrewDto;
import com.bhaikacms.addCrewAndLang.entity.Crew;
import com.bhaikacms.addCrewAndLang.repository.CrewRepository;
import com.bhaikacms.addCrewAndLang.services.AddCrew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddCrewImpl implements AddCrew {

    @Autowired
    CrewRepository crewRepository;

    @Override
    public boolean addCrewDetails(CrewDto crewDto) {
        Crew crew=new Crew();
        crew.setCrewName(crewDto.getCrewName());
        boolean  result = (crewRepository.findByCrewName(crewDto.getCrewName())==null?false:true);
        System.out.println("result : "+result);
        if(result){
            return false;
        }
        else{
            crewRepository.save(crew);
            return true;
        }
    }
}
