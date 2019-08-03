package com.example.scheduler.entity;

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
@Table(name = SchedulingEntity.TABLE_NAME)
public class SchedulingEntity {
    public static final String TABLE_NAME = "TEST";
    private Boolean isAlive;
    private Long expiryDate;
    private Long startDate;
    @Id
    private String pid;


}
