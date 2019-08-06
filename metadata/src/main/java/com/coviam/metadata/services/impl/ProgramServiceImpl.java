package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.Change;
import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.ProgramRequest;
import com.coviam.metadata.dto.response.EmailResponse;
import com.coviam.metadata.entity.Category;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.repository.CategoryRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SearchUtility searchUtility;

    @Autowired
    private AuditUtility auditUtility;

    @Transactional
    @Override
    public Program addProgram(ProgramRequest programRequest) {

        Program program = new Program();
        BeanUtils.copyProperties(programRequest, program);
        Category category = categoryRepository.findById(programRequest.getCategory().getId()).orElse(new Category());
/*
        String assetId,
        String assest,
        String userEmail,
        List<Change> changes*/

        program.setCategory(category);
        program.setCreationDate(System.currentTimeMillis());
        Program program1 = Optional.of(programRepository.save(program)).orElse(new Program());

        auditUtility.addAudit(program1.getId(),
                program1.getName(),
                programRequest.getUserEmail(),
                new ArrayList<Change>());

        log.info("Adding program programName: {}", program.getName());

        searchUtility.addToSearch(program1);


        return program1;
    }

    @Transactional
    @Override
    public Boolean editProgram(ProgramRequest programRequest) {
        if (!programRepository.existsById(programRequest.getId())) {
            return Boolean.FALSE;
        }
        Program program = programRepository.findById(programRequest.getId()).get();

        List<Change> changes = new ArrayList<>();
        String fieldChanged = "";
        String oldValue = "";
        String newValue = "";
        if (!program.getName().equals(programRequest.getName())) {
            fieldChanged = "Name";
            oldValue = program.getName();
            newValue = programRequest.getName();
            changes.add(new Change(fieldChanged, oldValue, newValue));
        }
        if (!program.getDescription().equals(programRequest.getDescription())) {
            fieldChanged = "Description";
            oldValue = program.getName();
            newValue = programRequest.getName();
            changes.add(new Change(fieldChanged, oldValue, newValue));
        }
        if (!program.getParentalRating().equals(programRequest.getParentalRating())) {
            fieldChanged = "Parental rating";
            oldValue = program.getParentalRating();
            newValue = programRequest.getParentalRating();
            changes.add(new Change(fieldChanged, oldValue, newValue));
        }
        if (!program.getType().equals(programRequest.getType())) {
            fieldChanged = "Type";
            oldValue = program.getType();
            newValue = programRequest.getType();
            changes.add(new Change(fieldChanged, oldValue, newValue));
        }
        if (!program.getStartDate().equals(programRequest.getStartDate())) {
            fieldChanged = "Start Date";
            oldValue = program.getStartDate().toString();
            newValue = programRequest.getStartDate().toString();
            changes.add(new Change(fieldChanged, oldValue, newValue));
        }
        if (!program.getExpiryDate().equals(programRequest.getExpiryDate())) {
            fieldChanged = "Expiry Date";
            oldValue = program.getExpiryDate().toString();
            newValue = programRequest.getExpiryDate().toString();
            changes.add(new Change(fieldChanged, oldValue, newValue));
        }

        BeanUtils.copyProperties(programRequest, program);
        programRepository.save(program);
        log.info("Program update programId: {} ", programRequest.getId());
        auditUtility.editAudit(program.getId(),
                program.getName(),
                programRequest.getUserEmail(),
                changes);

        return Boolean.TRUE;
    }

    // we are using cascade delete constraint
    @Transactional
    @Override
    public Boolean deleteProgramById(DeleteRequest deleteRequest) {
        String programId = deleteRequest.getId();
        Program program = programRepository.findById(programId).orElse(new Program());

        programRepository.deleteById(programId);
        log.warn("Cascade delete action will be performed for programId: {}", programId);
        auditUtility.deleteAudit(program.getId(),
                program.getName(),
                deleteRequest.getUserEmail(),
                new ArrayList<Change>());
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