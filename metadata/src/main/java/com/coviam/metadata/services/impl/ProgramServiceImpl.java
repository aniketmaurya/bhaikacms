package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Program;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.response.ProgramResponse;
import com.coviam.metadata.services.ProgramServices;
import com.coviam.metadata.services.SeasonServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ProgramResponse addProgram(Program program) {
        ProgramResponse programResponse = ProgramResponse.builder().program(program).build();
        try {
            programResponse.setSuccessful(Boolean.TRUE);
            programRepository.save(program);
        } catch (Exception e) {
            programResponse.setSuccessful(Boolean.FALSE);
            log.error("Exception while creating program with programId: {}", program.getId());
        }
        return programResponse;
    }

    @Override
    public ProgramResponse deleteProgramById(String programId) {
        ProgramResponse programResponse = ProgramResponse.builder().build();
        try {
            Page<Season> seasonIterable = seasonRepository.findByProgramId(programId, PageRequest.of(0,10));
            for (Season season : seasonIterable) {
                seasonServices.deleteSeasonById(season.getId());
            }
            programRepository.deleteById(programId);
            programResponse.setSuccessful(Boolean.TRUE);
        } catch (Exception e) {
            log.error("Exception while deleting program with programId: {}", programId);
            programResponse.setSuccessful(Boolean.FALSE);
        }

        return programResponse;
    }
}