package com.coviam.metadata.services;

import com.coviam.metadata.dto.ProgramDto;
import com.coviam.metadata.entity.Program;

import java.util.List;

public interface ProgramServices {

    boolean addProgram(Program program);

    List<ProgramDto> getAllPrograms(Integer pageNumber, Integer size);

    List<ProgramDto> getProgramsByAuthor(String userName);

    ProgramDto getProgramByProgramId(String programId);

    // todo on del prgm -> del all under it
    boolean deleteProgramByProgramId(String programId);

}
