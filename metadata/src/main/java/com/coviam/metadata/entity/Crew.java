package com.coviam.metadata.entity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

public class Crew {

    String id;
    String role;
    String name;

    @JoinTable(
            name = "program_crew",
            joinColumns = @JoinColumn(name = "programId"),
            inverseJoinColumns = @JoinColumn(name = "crewId")
    )
    Program program;
}
