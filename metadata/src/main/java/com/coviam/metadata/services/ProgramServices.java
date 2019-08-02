package com.coviam.metadata.services;

import com.coviam.metadata.entity.Program;

import java.util.Optional;

public interface ProgramServices {

    Optional<Program> addProgram(Program program);

    Boolean deleteProgramById(String programId);

    Optional<Program> getProgramById(String programId);

}
