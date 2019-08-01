package com.coviam.metadata.entity;

import java.util.HashMap;
import java.util.List;

public class Program {

    String id;
    String programType;
    String description;
    String programName;
    String parentalRating;
    HashMap<String, List<String>> crewRole;
    List<String> keywords;
    Language languageId;


}
