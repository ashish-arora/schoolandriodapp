package com.tresflex.schoolapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashish on 05/11/15.
 */
public class Attendance {

    private String id;
    private Integer present;
    private String studentId;
    private String groupId;
    private Long timestamp;

    public Attendance(String id, Integer present, String studentId, String groupId, Long timestamp) {
        this.id = id;
        this.present = present;
        this.studentId = studentId;
        this.groupId = groupId;
        this.timestamp = timestamp;
    }

    public static Attendance convertFromJson(JSONObject attendance) throws JSONException {
        Long timestamp = attendance.getLong("ts");
        String id = attendance.getString("id");
        Integer present = attendance.getInt("present");
        String studentId = attendance.getJSONObject("student").getString("id");
        String groupId = attendance.getJSONObject("student").getJSONObject("group").getString("id");
        return new Attendance(id, present, studentId, groupId, timestamp);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPresent() {
        return present;
    }

    public void setPresent(Integer present) {
        this.present = present;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
