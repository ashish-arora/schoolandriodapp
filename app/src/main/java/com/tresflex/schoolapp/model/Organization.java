package com.tresflex.schoolapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashish on 04/11/15.
 */
public class Organization {
    private String id;
    private String name;
    private String address;
    private String timestamp;

    public Organization(String id, String name, String address, String timestamp) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.timestamp = timestamp;
    }

    public static Organization convertFromJson(JSONObject organization) throws JSONException {
        String organizationId = organization.getString("id");
        String organizationName = organization.getString("name");
        String organizationAddress = organization.getString("address");
        String organizationTs = organization.getString("ts");
        return new Organization(organizationId, organizationName, organizationAddress, organizationTs);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
