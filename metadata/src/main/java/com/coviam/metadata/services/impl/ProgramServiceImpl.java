package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.ProgramRequest;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.ProgramServices;
import com.coviam.metadata.services.SeasonServices;
import com.coviam.metadata.utility.AuditUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    private final String ADD = "ADD";
    private final String UPDATE = "UPDATE";
    private final String DELETE = "DELETE";

    @Transactional
    @Override
    public Program addProgram(Program program) {
        log.info("Adding program programName: {}", program.getName());
        AuditUtility.programAudit(new Program(), program, ADD);
        return Optional.of(programRepository.save(program)).orElse(new Program());
    }

    @Override
    public Boolean editProgram(ProgramRequest programRequest) {
        if (!programRepository.existsById(programRequest.getId())) {
            return Boolean.FALSE;
        }
        Program program = programRepository.findById(programRequest.getId()).get();
        BeanUtils.copyProperties(programRequest, program);
        programRepository.save(program);
        log.info("Program update programId: {} ", programRequest.getId());
        return Boolean.TRUE;
    }

    // we are using cascade delete constraint
    @Transactional
    @Override
    public Boolean deleteProgramById(String programId) {
        Program program = programRepository.findById(programId).orElse(new Program());
        AuditUtility.programAudit(program, program, DELETE);
        programRepository.deleteById(programId);
        log.warn("Cascade delete action will be performed for programId: {}", programId);
        return Boolean.TRUE;
    }

    @Override
    public Program getProgramById(String programId) {
        return programRepository.findById(programId).orElse(new Program());
    }

}