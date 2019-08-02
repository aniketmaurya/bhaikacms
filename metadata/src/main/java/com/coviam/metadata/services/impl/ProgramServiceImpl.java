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

import java.util.List;
import java.util.Optional;

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
    public Optional<Program> addProgram(Program program) {
        try {
            programRepository.save(program);
            log.debug("Added/updated program {}", program.getProgramName());
        } catch (Exception e) {
            log.error("Exception while creating program with programId: {}", program.getId());
            return Optional.empty();
        }
        return Optional.of(program);
    }

    // optimise
    @Override
    public Boolean deleteProgramById(String programId) {
        try {
            List<Season> seasonIterable = seasonRepository.findByProgramId(programId);
            for (Season season : seasonIterable) {
                seasonServices.deleteBySeasonId(season.getId());
            }
            programRepository.deleteById(programId);

        } catch (Exception e) {
            log.error("Exception while deleting program with programId: {}", programId);
            return false;
        }

        return true;
    }

    @Override
    public Optional<Program> getProgramById(String programId) {
        Optional<Program> program = Optional.empty();
        try {
            program = programRepository.findById(programId);
        } catch (Exception e) {
            log.error("Couldn't get program for program Id: {}", programId);
        }
        return program;
    }
}