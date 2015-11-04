package com.tresflex.schoolapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashish on 04/11/15.
 */
public class Group {

    private String id;
    private String name;
    private Integer total_student;
    private Long timestamp;

    public Group(String id, String name, Long timestamp) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
        this.timestamp = timestamp;
    }

    public static Group convertFromJson(JSONObject group) throws JSONException {
        String classID = class_data.getString("class_id");
        String className = class_data.getString("class_name");
        Long classTs = class_data.getLong("ts");
        return new Group(classID, className, classTs);
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
