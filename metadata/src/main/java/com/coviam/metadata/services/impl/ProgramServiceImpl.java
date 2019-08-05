package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.ProgramRequest;
import com.coviam.metadata.dto.response.EmailResponse;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.ProgramServices;
import com.coviam.metadata.services.SeasonServices;
import com.coviam.metadata.utility.AuditUtility;
import com.coviam.metadata.utility.SearchUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProgramServiceImpl implements ProgramServices {

    private final String ADD = "ADD";
    private final String UPDATE = "UPDATE";
    private final String DELETE = "DELETE";
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private SeasonServices seasonServices;

    @Transactional
    @Override
    public Program addProgram(ProgramRequest programRequest) {

        Program program = new Program();
        BeanUtils.copyProperties(programRequest, program);

        log.info("Adding program programName: {}", program.getName());
        AuditUtility.addAudit("", "ADDED A NEW PROGRAM",
                programRequest.getUserId(), "PROGRAM");

        program.setCreationDate(System.currentTimeMillis());
        Program program1 = Optional.of(programRepository.save(program)).orElse(new Program());

        SearchUtility.addToSearch(program1);


        return program1;
    }

    @Override
    public Boolean editProgram(ProgramRequest programRequest) {
        if (!programRepository.existsById(programRequest.getId())) {
            return Boolean.FALSE;
        }
        Program program = programRepository.findById(programRequest.getId()).get();

        AuditUtility.editAudit(program.toString(), programRequest.toString(),
                programRequest.getUserId(), "PROGRAM");

        BeanUtils.copyProperties(programRequest, program);
        programRepository.save(program);
        log.info("Program update programId: {} ", programRequest.getId());

        return Boolean.TRUE;
    }

    // we are using cascade delete constraint
    @Transactional
    @Override
    public Boolean deleteProgramById(DeleteRequest deleteRequest) {
        String programId = deleteRequest.getId();
        Program program = programRepository.findById(programId).orElse(new Program());
//        AuditUtility.programAudit(program, program, DELETE, deleteRequest.getUseId());
        AuditUtility.deleteAudit(program.toString(), "",
                deleteRequest.getUserId(), "PROGRAM");

        programRepository.deleteById(programId);
        log.warn("Cascade delete action will be performed for programId: {}", programId);
        return Boolean.TRUE;
    }

    @Override
    public Program getProgramById(String programId) {
        return programRepository.findById(programId).orElse(new Program());
    }

    @Override
    public Page<Program> getAllSingleVideoProgram(Integer pageNumber, Integer pageSize) {
        return programRepository.findByType("Single video program",
                PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllSeasonalVideoProgram(Integer pageNumber, Integer pageSize) {
        return programRepository.findByType("Seasonal video program",
                PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllMultiVideoProgram(Integer pageNumber, Integer pageSize) {
        return programRepository.findByType("Multi video program",
                PageRequest.of(pageNumber, pageSize));
    }

    //added by apoorv singh
    @Override
    public List<EmailResponse> sendExpiredToEmail() {
        List<EmailResponse> expiredResponse = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        Page<Program> expired = programRepository.findByStartDateLessThan(currentTime, new PageRequest(0, 10));
        for (Program temp : expired) {
            EmailResponse emailResponse = new EmailResponse();
            emailResponse.setExpiryDate(temp.getExpiryDate());
            emailResponse.setId(temp.getUserId());
            emailResponse.setStartDate(temp.getStartDate());
            expiredResponse.add(emailResponse);
        }
        return expiredResponse;
    }
}