package com.tresflex.schoolapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashish on 03/11/15.
 */
public class StudentInfo {
    private String id;
    private String firstName;
    private String lastName;
    private String msisdn;
    private String email;
    private Integer rollNo;
    private String groupId;

    public StudentInfo(String student_id, String firstName, String lastName, Integer rollNo, String msisdn, String email, String groupId){
        this.id = student_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNo = rollNo;
        this.email = email;
        this.msisdn = msisdn;
        this.groupId = groupId;
    }

    public StudentInfo(String student_id, String firstName, String lastName, Integer rollNo, String groupId){
        this.id = student_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNo = rollNo;
        this.groupId = groupId;
        this.email = "";
        this.msisdn = "";
    }

    public static StudentInfo convertFromJson(JSONObject studentData) throws JSONException {
        String firstName = studentData.getString("first_name");
        String lastName = studentData.getString("last_name");
        String studentId = studentData.getString("id");
        String groupId = studentData.getJSONObject("group").getString("id");
        Integer rollNo = studentData.getInt("roll_no");
        String studentMsisdn="";
        String studentEmail = "";
        if (studentData.has("msisdn"))
            studentMsisdn = studentData.getString("msisdn");
        if (studentData.has("email"))
            studentEmail = studentData.getString("email");
        return new StudentInfo(studentId, firstName, lastName, rollNo, studentMsisdn, studentEmail, groupId);
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
