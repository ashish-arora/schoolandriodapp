package com.tresflex.schoolapp.model;

/**
 * Created by Admin on 20-10-2015.
 */
public class EventsFeed {

    String titleStr, descriptionStr, dateStr, dateStr1, homeImgUrl, schoolImgUrl;
    int homeImg, schoolImg;

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getDescriptionStr() {
        return descriptionStr;
    }

    public void setDescriptionStr(String descriptionStr) {
        this.descriptionStr = descriptionStr;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
    public String getDateStr1() {
        return dateStr1;
    }

    public void setDateStr1(String dateStr1) {
        this.dateStr1 = dateStr1;
    }

    public int getHomeImg() {
        return homeImg;
    }

    public void setHomeImg(int homeImg) {
        this.homeImg = homeImg;
    }

    public int getSchoolImg() {
        return schoolImg;
    }

    public void setSchoolImg(int schoolImg) {
        this.schoolImg = schoolImg;
    }

    public String getHomeUrl() {
        return homeImgUrl;
    }

    public void setHomeurl(String schoolImgUrl) {
        this.schoolImgUrl = schoolImgUrl;
    }

    public String getSchoolUrl() {
        return homeImgUrl;
    }

    public void setSchoolurl(String schoolImgUrl) {
        this.schoolImgUrl = schoolImgUrl;
    }
}
