package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.ProgramDto;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.services.ProgramServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProgramServiceImpl implements ProgramServices {

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public boolean addProgram(Program program) {
        try {
            programRepository.save(program);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    @Override
    public List<ProgramDto> getAllPrograms(Integer pageNumber, Integer size) {

        Page<Program> programPage = programRepository.findAll(new PageRequest(pageNumber, size));
        List<Program> programList = programPage.getContent();
        List<ProgramDto> programDtoList = new ArrayList<>();
        for (Program program : programList) {
            ProgramDto programDto = new ProgramDto();
            programDtoList.add(programDto);

        }
        return programDtoList;
    }

    @Override
    public List<ProgramDto> getProgramsByAuthor(String author) {
        List<ProgramDto> programDtoList = new ArrayList<>();
        List<Program> programList = programRepository.findByAuthor(author);
        if (programList == null) return null;
        BeanUtils.copyProperties(programList, programDtoList);

        return programDtoList;
    }

    @Override
    public ProgramDto getProgramByProgramId(String programId) {

        Optional<Program> program = programRepository.findById(programId);
        if (program == null) return null;

        ProgramDto programDto = new ProgramDto();
        BeanUtils.copyProperties(program, programDto);

        return programDto;
    }

    @Override
    public boolean deleteProgramByProgramId(String programId) {
        Optional<Program> program = programRepository.findById(programId);
        if (program == null) return false;

        programRepository.deleteById(programId);
        return true;
    }

}
