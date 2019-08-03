package com.bhaikacms.scheduler.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = Program.TABLE_NAME)
public class Program{
    public static final String TABLE_NAME = "PROGRAM";
    private static final String ID_COLUMN = "pId";

    @Id
    @Column(name = Program.ID_COLUMN)
        private String pId;
        private String programName;
        private Timestamp startDate;
        private Timestamp expDate;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getExpDate() {
        return expDate;
    }

    public void setExpDate(Timestamp expDate) {
        this.expDate = expDate;
    }
}
