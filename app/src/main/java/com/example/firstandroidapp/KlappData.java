package com.example.firstandroidapp;

public class KlappData {
    private String name;
    private String description;
    private String risetime;
    private String descenttime;
    private String dateaccessible;
    private String status;
    private String startingpoint;
    private String endpoint;

    private String federalstate;
    private String difficulty;
    private String linktowebsite;
    private String userid;
    private String dateCreated;
    private String imageList;

    public KlappData(String name, String description, String risetime, String descenttime, String dateaccessible, String status, String startingpoint, String endpoint, String federalstate, String difficulty, String linktowebsite, String userid, String dateCreated, String imageList) {
        this.name = name;
        this.description = description;
        this.risetime = risetime;
        this.descenttime = descenttime;
        this.dateaccessible = dateaccessible;
        this.status = status;
        this.startingpoint = startingpoint;
        this.endpoint = endpoint;
        this.federalstate = federalstate;
        this.difficulty = difficulty;
        this.linktowebsite = linktowebsite;
        this.userid = userid;
        this.dateCreated = dateCreated;
        this.imageList = imageList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRisetime() {
        return risetime;
    }

    public void setRisetime(String risetime) {
        this.risetime = risetime;
    }

    public String getDescenttime() {
        return descenttime;
    }

    public void setDescenttime(String descenttime) {
        this.descenttime = descenttime;
    }

    public String getDateaccessible() {
        return dateaccessible;
    }

    public void setDateaccessible(String dateaccessible) {
        this.dateaccessible = dateaccessible;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        return this.status = status;
    }

    public String getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getFederalstate() {
        return federalstate;
    }

    public void setFederalstate(String federalstate) {
        this.federalstate = federalstate;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLinktowebsite() {
        return linktowebsite;
    }

    public void setLinktowebsite(String linktowebsite) {
        this.linktowebsite = linktowebsite;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }
}
