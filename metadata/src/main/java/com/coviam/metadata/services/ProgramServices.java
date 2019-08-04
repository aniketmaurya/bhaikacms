package com.coviam.metadata.services;

import com.coviam.metadata.dto.request.ProgramRequest;
import com.coviam.metadata.entity.Program;

public interface ProgramServices {

    Program addProgram(Program program);

    Boolean editProgram(ProgramRequest program);

    Boolean deleteProgramById(String programId);

    Program getProgramById(String programId);


}
