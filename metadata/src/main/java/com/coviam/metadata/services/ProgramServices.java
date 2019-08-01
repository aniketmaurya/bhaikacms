package com.coviam.metadata.services;

import com.coviam.metadata.entity.Program;

import java.util.List;

public interface ProgramServices {

    boolean addProgram(Program program);

    List<Program> getAllPrograms();

    List<Program> getProgramsByUser(String userName);

    List<Program> getProgramsByKeywords(List<String> keywords);

}
