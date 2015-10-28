package com.tresflex.schoolapp.model;

import java.util.List;

/**
 * Created by Admin on 22-10-2015.
 */
public class TeacherAddStudent {

    String studentNameStr, fatherNameStr, motherNameStr;
    Long rollNoStr, fatherMobileNoStr, motherMobileNoStr;
    List<TeacherAddStudent> addStudentList;

    public String getStudentName() {
        return studentNameStr;
    }

    public void setStudentName(String studentNameStr) {
        this.studentNameStr = studentNameStr;
    }

    public String getFatherName() {
        return fatherNameStr;
    }

    public void setFatherName(String  fatherNameStr) {
        this. fatherNameStr =  fatherNameStr;
    }

    public String getMotherName() {
        return motherNameStr;
    }

    public void setMotherName(String motherNameStr) {
        this.motherNameStr = motherNameStr;
    }
    public  Long getRollNo() {
        return rollNoStr;
    }

    public void setRollNo(Long rollNoStr) {
        this.rollNoStr = rollNoStr;
    }
    public  Long getFatherMobileNo() {
        return fatherMobileNoStr;
    }

    public void setFatherMobileNo(Long fatherMobileNoStr) {
        this.fatherMobileNoStr = fatherMobileNoStr;
    }
    public  Long getMotherMobileNo() {
        return motherMobileNoStr;
    }

    public void setMotherMobileNo(Long motherMobileNoStr) {
        this.motherMobileNoStr = motherMobileNoStr;
    }



}
