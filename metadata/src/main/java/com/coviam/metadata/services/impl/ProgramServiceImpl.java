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
import org.joda.time.LocalDate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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


    @Override
    public List<Program> addProgramByBulkUpload(File csvFile) {
        String line = "";
        String csvSplitBy = ",";
        List<Program> programList = new ArrayList<>();
        try {
            FileReader file = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(file);
            line = br.readLine();
            String[] headers = line.split(csvSplitBy);
            if (headers[0].equalsIgnoreCase("Program Type") && headers[1].equalsIgnoreCase("Description")
                    && headers[2].equalsIgnoreCase("Program Name") && headers[3].equalsIgnoreCase("Parental Rating")
                    && headers[4].equalsIgnoreCase("Keywords") && headers[5].equalsIgnoreCase("Languages")
                    && headers[6].equalsIgnoreCase("Start Date") && headers[7].equalsIgnoreCase("Expiry Date")
                    && headers[8].equalsIgnoreCase("Category") && headers[9].equalsIgnoreCase("Thumbnail Image Url")
                    && headers[10].equalsIgnoreCase("Avatar Image Url")
                    && headers[11].equalsIgnoreCase("userId")) {
                while ((line = br.readLine()) != null) {
                    String[] records = line.split(csvSplitBy);
                    HashMap<String, String> images = new HashMap<>();
                    images.put("Thumbnail", records[9]);
                    images.put("Avatar", records[10]);
                    ProgramRequest programRequest = ProgramRequest.builder()
                            .type(records[0])
                            .description(records[1])
                            .name(records[2])
                            .parentalRating(records[3])
                            .keywords(records[4])
                            .languages(records[5])
                            .category(categoryRepository.getCategoryByCategoryName(records[8]))
                            .isAlive(Boolean.TRUE)
                            .imgUrls(images)
                            .startDate((new SimpleDateFormat("dd/MM/yyyy").parse(records[6])).getTime())
                            .expiryDate((new SimpleDateFormat("dd/MM/yyyy").parse(records[7])).getTime())
                            .userId(records[11])
                            .build();
                    System.out.println(programRequest);
                    Program program = addProgram(programRequest);
                    programList.add(program);
                }
            }
        } catch (Exception e) {
            log.debug("Error while uploading program :" + e.getMessage());
        }
        return programList;
    }


    //added by apoorv singh
    @Override
    public List<EmailResponse> sendExpiredToEmail() {
        List<EmailResponse> expiredResponse = new ArrayList<>();
        Long currentTime = System.currentTimeMillis();
        Page<Program> expired = programRepository.findByStartDateLessThan(currentTime, PageRequest.of(0, 10));
        for (Program temp : expired) {
            EmailResponse emailResponse = new EmailResponse();
            emailResponse.setExpiryDate(temp.getExpiryDate());
            emailResponse.setId(temp.getUserId());
            emailResponse.setStartDate(temp.getStartDate());
            emailResponse.setId(temp.getId());
            expiredResponse.add(emailResponse);
        }
        return expiredResponse;

    }

    @Override
    public List<EmailResponse> sendAboutToExpire() {
        List<EmailResponse> aboutToExpire = new ArrayList<>();
        Long toExpire = (LocalDate.now().plusDays(2)).toDate().getTime();
        Page<Program> allList = programRepository.findByExpiryDateLessThan(toExpire, PageRequest.of(0, 10));
        for (Program temp : allList) {
            EmailResponse emailResponse = new EmailResponse();
            emailResponse.setExpiryDate(temp.getExpiryDate());
            emailResponse.setId(temp.getUserId());
            emailResponse.setStartDate(temp.getStartDate());
            emailResponse.setId(temp.getId());
            aboutToExpire.add(emailResponse);

        }
        return aboutToExpire;
    }


    //=============================================
    //added by Sunil
    @Override
    public Page<Program> getAllSingleVideoProgramSortByNameAsc(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByNameAsc("Single video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllSingleVideoProgramSortByNameDesc(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByNameDesc("Single video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllSingleVideoProgramSortByNewestFirst(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByCreationDateDesc("Single video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllSingleVideoProgramSortByOldestFirst(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByCreationDateAsc("Single video program", PageRequest.of(pageNumber, pageSize));
    }

    // getAllMultiVideoProgramSortByNameAsc
    @Override
    public Page<Program> getAllMultiVideoProgramSortByNameAsc(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByNameAsc("Multi video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllMultiVideoProgramSortByNameDesc(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByNameDesc("Multi video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllMultiVideoProgramSortByNewestFirst(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByCreationDateDesc("Multi video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllMultiVideoProgramSortByOldestFirst(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByCreationDateAsc("Multi video program", PageRequest.of(pageNumber, pageSize));
    }

    //getAllSeasonalVideoProgram
    @Override
    public Page<Program> getAllSeasonalVideoProgramSortByNameAsc(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByNameAsc("Seasonal video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllSeasonalVideoProgramSortByNameDesc(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByNameDesc("Seasonal video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllSeasonalVideoProgramSortByNewestFirst(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByCreationDateDesc("Seasonal video program", PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> getAllSeasonalVideoProgramSortByOldestFirst(Integer pageNumber, Integer pageSize) {
        return programRepository.findByTypeOrderByCreationDateAsc("Seasonal video program", PageRequest.of(pageNumber, pageSize));
    }

    //Filter elements
    @Override
    public Page<Program> filterByProgramName(Integer pageNumber, Integer pageSize, String name) {
        return programRepository.findByNameOrderByNameAsc(name, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> filterByProgramType(Integer pageNumber, Integer pageSize, String type) {
        return programRepository.findByTypeOrderByNameAsc(type, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Program> filterByProgramLanguage(Integer pageNumber, Integer pageSize, String languages) {
        return programRepository.findByLanguagesOrderByNameAsc(languages, PageRequest.of(pageNumber, pageSize));
    }


}