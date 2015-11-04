package com.tresflex.schoolapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashish on 04/11/15.
 */
public class Parent {

    private String id;
    private String firstName;
    private String lastName;
    private String msisdn;
    private String email;
    private Long timestamp;

    public Parent (String id, String firstName, String lastName, String msisdn, String email, Long timestamp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.msisdn = msisdn;
        this.email = email;
        this.timestamp = timestamp;
    }

    public static Parent convertFromJson(JSONObject data) throws JSONException {
        String first_name = data.getString("first_name");
        String last_name = data.getString("last_name");
        String email = data.getString("email");
        String msisdn = data.getString("msisdn");
        String userId = data.getString("id");
        Long timestamp = data.getLong("ts");
        return new Parent(userId, first_name, last_name, email, msisdn, timestamp);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
