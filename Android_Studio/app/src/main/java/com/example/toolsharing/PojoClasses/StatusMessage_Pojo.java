package com.example.toolsharing.PojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusMessage_Pojo {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("AdminAccess")
    @Expose
    private String adminaccess;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("StudentRegis")
    @Expose
    private List<StudentRegisList_Pojo> studentRegis = null;
    @SerializedName("ToolsList")
    @Expose
    private List<ToolsList_Pojo> toolsList = null;
    @SerializedName("SearchToolsList")
    @Expose
    private List<SearchToolsList_Pojo> searchToolsList = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getAdminaccess() {
        return adminaccess;
    }

    public void setAdminaccess(String adminaccess) {
        this.adminaccess = adminaccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<StudentRegisList_Pojo> getStudentRegis() {
        return studentRegis;
    }

    public void setStudentRegis(List<StudentRegisList_Pojo> studentRegis) {
        this.studentRegis = studentRegis;
    }

    public List<ToolsList_Pojo> getToolsList() {
        return toolsList;
    }

    public void setToolsList(List<ToolsList_Pojo> toolsList) {
        this.toolsList = toolsList;
    }

    public List<SearchToolsList_Pojo> getSearchToolsList() {
        return searchToolsList;
    }

    public void setSearchToolsList(List<SearchToolsList_Pojo> searchToolsList) {
        this.searchToolsList = searchToolsList;
    }

}
