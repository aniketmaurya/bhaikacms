package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Program;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.ProgramServices;
import com.coviam.metadata.services.SeasonServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("Adding program programName: {}", program.getName());
        return Optional.of(programRepository.save(program));
    }

    // we are using cascade delete constraint
    @Transactional
    @Override
    public Boolean deleteProgramById(String programId) {
        programRepository.deleteById(programId);
        log.warn("Cascade delete action will be performed for programId: {}", programId);
        return Boolean.TRUE;
    }

    @Override
    public Optional<Program> getProgramById(String programId) {
        return programRepository.findById(programId);
    }
}