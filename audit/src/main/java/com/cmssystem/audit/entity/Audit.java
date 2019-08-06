package com.cmssystem.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Audit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String auditId;
    private String asset;
    private String assetId;
    private String action;
    private Long actionTime;
    private String modifier;
    private String oldValue;
    private String newValue;
    private Integer flag; //0->metadata 1->user
}

