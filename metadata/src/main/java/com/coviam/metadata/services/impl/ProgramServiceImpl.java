package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Category;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.services.ProgramServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramServices {

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public boolean addProgram(Program program) {
        programRepository.save(program);
        return true;
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public List<Program> getProgramsByCategory(Category category) {
        return null;
    }

    @Override
    public List<Program> getProgramsByUser(String userName) {
        return null;
    }

    @Override
    public List<Program> getProgramsByKeywords(List<String> keywords) {
        return null;
    }
}
