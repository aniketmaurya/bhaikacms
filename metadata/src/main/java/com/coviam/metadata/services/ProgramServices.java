package com.coviam.metadata.services;

import com.coviam.metadata.entity.Program;

public interface ProgramServices {

    Boolean addProgram(Program program);

    // todo on del prgm -> del all under it : Done
    Boolean deleteProgramById(String programId);

}
