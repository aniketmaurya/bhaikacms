package com.coviam.metadata.services;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.ProgramRequest;
import com.coviam.metadata.dto.response.EmailResponse;
import com.coviam.metadata.entity.Program;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.List;

public interface ProgramServices {

    Program addProgram(ProgramRequest programRequest);

    Boolean editProgram(ProgramRequest program);

    Boolean deleteProgramById(DeleteRequest deleteRequest);

    Program getProgramById(String programId);

    Page<Program> getAllSingleVideoProgram(Integer pageNumber, Integer pageSize);

    Page<Program> getAllSeasonalVideoProgram(Integer pageNumber, Integer pageSize);

    Page<Program> getAllMultiVideoProgram(Integer pageNumber, Integer pageSize);

    List<EmailResponse> sendExpiredToEmail();

    List<EmailResponse> sendAboutToExpire();


    List<Program> addProgramByBulkUpload(File csvFile);


    //============================================================================================
    //Added by Sunil --> single video program
    Page<Program> getAllSingleVideoProgramSortByNameAsc(Integer pageNumber, Integer pageSize);

    Page<Program> getAllSingleVideoProgramSortByNameDesc(Integer pageNumber, Integer pageSize);

    Page<Program> getAllSingleVideoProgramSortByNewestFirst(Integer pageNumber, Integer pageSize);

    Page<Program> getAllSingleVideoProgramSortByOldestFirst(Integer pageNumber, Integer pageSize);

    // Multi video program
    Page<Program> getAllMultiVideoProgramSortByNameAsc(Integer pageNumber, Integer pageSize);

    Page<Program> getAllMultiVideoProgramSortByNameDesc(Integer pageNumber, Integer pageSize);

    Page<Program> getAllMultiVideoProgramSortByNewestFirst(Integer pageNumber, Integer pageSize);

    Page<Program> getAllMultiVideoProgramSortByOldestFirst(Integer pageNumber, Integer pageSize);

    //getAllSeasonalVideoProgram
    Page<Program> getAllSeasonalVideoProgramSortByNameAsc(Integer pageNumber, Integer pageSize);

    Page<Program> getAllSeasonalVideoProgramSortByNameDesc(Integer pageNumber, Integer pageSize);

    Page<Program> getAllSeasonalVideoProgramSortByNewestFirst(Integer pageNumber, Integer pageSize);

    Page<Program> getAllSeasonalVideoProgramSortByOldestFirst(Integer pageNumber, Integer pageSize);

    // filterByProgramName
    Page<Program> filterByProgramName(Integer pageNumber, Integer pageSize, String name);

    Page<Program> filterByProgramType(Integer pageNumber, Integer pageSize, String type);

    Page<Program> filterByProgramLanguage(Integer pageNumber, Integer pageSize, String languages);


}
