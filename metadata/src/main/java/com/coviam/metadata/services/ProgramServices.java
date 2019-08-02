package com.coviam.metadata.services;

import com.coviam.metadata.entity.Program;
import com.coviam.metadata.response.ProgramResponse;

public interface ProgramServices {

    ProgramResponse addProgram(Program program);

    // todo on del prgm -> del all under it : Done
    ProgramResponse deleteProgramById(String programId);

}
