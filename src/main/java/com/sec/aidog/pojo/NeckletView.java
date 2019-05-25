package com.sec.aidog.pojo;

import java.util.Date;

/**
 * Created by WangZJ on 2019/5/25.
 */
public class NeckletView {

    private Integer id;
    private String necId;
    private String pillcode;
    private String dosingstatus;   //投药状态
    private Date firstDosingTime;
    private Date nextDosingTime;
    private Integer leftnum;
    private String power;
    private String temperature;
    private String confstatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNecId() {
        return necId;
    }

    public void setNecId(String necId) {
        this.necId = necId;
    }

    public String getPillcode() {
        return pillcode;
    }

    public void setPillcode(String pillcode) {
        this.pillcode = pillcode;
    }

    public String getDosingstatus() {
        return dosingstatus;
    }

    public void setDosingstatus(String dosingstatus) {
        this.dosingstatus = dosingstatus;
    }

    public Date getFirstDosingTime() {
        return firstDosingTime;
    }

    public void setFirstDosingTime(Date firstDosingTime) {
        this.firstDosingTime = firstDosingTime;
    }

    public Date getNextDosingTime() {
        return nextDosingTime;
    }

    public void setNextDosingTime(Date nextDosingTime) {
        this.nextDosingTime = nextDosingTime;
    }

    public Integer getLeftnum() {
        return leftnum;
    }

    public void setLeftnum(Integer leftnum) {
        this.leftnum = leftnum;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getConfstatus() {
        return confstatus;
    }

    public void setConfstatus(String confstatus) {
        this.confstatus = confstatus;
    }
}
