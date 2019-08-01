package com.coviam.metadata.dto;

import com.coviam.metadata.entity.Language;

import java.util.List;

public class ProgramDto {

    String id;

    String programType;

    String description;

    String programName;

    String parentalRating;

    List<String> keywords;

    List<Language> languageId;

    String programImgUrl;

    Long startDate;

    Long expiryDate;

    Boolean isAlive;

    private String userId;

    private String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getParentalRating() {
        return parentalRating;
    }

    public void setParentalRating(String parentalRating) {
        this.parentalRating = parentalRating;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<Language> getLanguageId() {
        return languageId;
    }

    public void setLanguageId(List<Language> languageId) {
        this.languageId = languageId;
    }

    public String getProgramImgUrl() {
        return programImgUrl;
    }

    public void setProgramImgUrl(String programImgUrl) {
        this.programImgUrl = programImgUrl;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
