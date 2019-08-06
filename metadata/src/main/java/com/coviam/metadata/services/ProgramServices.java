package com.coviam.metadata.services;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.ProgramRequest;
import com.coviam.metadata.dto.response.EmailResponse;
import com.coviam.metadata.dto.response.ProgramResponse;
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

    List<ProgramResponse> addProgramByBulkUpload(File csvFile);


}
