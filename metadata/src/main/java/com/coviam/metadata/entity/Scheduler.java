package com.coviam.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Scheduler.TABLE_NAME)
public class Scheduler {

    public static final String TABLE_NAME = "Scheduler";

    @Id
    private String id;

    private Boolean isAlive;
    private Long expiryDate;
    private Long startDate;


}

