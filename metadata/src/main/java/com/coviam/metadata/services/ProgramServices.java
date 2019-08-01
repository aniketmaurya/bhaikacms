package com.coviam.metadata.services;

import com.coviam.metadata.entity.Category;
import com.coviam.metadata.entity.Program;

import java.util.List;

public interface ProgramServices {

    boolean addProgram(Program program);

    List<Program> getAllPrograms();

    // confusion
    List<Program> getProgramsByCategory(Category category);

    List<Program> getProgramsByUser(String userId);

}
