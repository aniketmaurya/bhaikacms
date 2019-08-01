package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Program;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.ProgramServices;
import com.coviam.metadata.services.SeasonServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProgramServiceImpl implements ProgramServices {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SeasonServices seasonServices;

    @Override
    public Boolean addProgram(Program program) {
        try {
            programRepository.save(program);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteProgramById(String programId) {
        try {
            Iterable<Season> seasonIterable = seasonRepository.findByProgramId(programId);
            for (Season season : seasonIterable) {
                seasonServices.deleteSeasonById(season.getId());
            }
            programRepository.deleteById(programId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}