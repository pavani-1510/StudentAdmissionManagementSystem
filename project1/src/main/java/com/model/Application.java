package com.model;

import java.io.Serializable;

public class Application implements Serializable {
    private String username;
    private String fullName;
    private String mobileNumber;
    private String emailId;
    private String gender;
    private String twelfthMarkSheetLink; // Changed from File to String
    private String branch;
    private String status; // Added field for application status

    public Application(String username, String fullName, String mobileNumber, String emailId,
                       String gender, String twelfthMarkSheetLink, String branch, String status) {
        this.username = username;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.gender = gender;
        this.twelfthMarkSheetLink = twelfthMarkSheetLink;
        this.branch = branch;
        this.status = status; // Initialize status
    }

    public Application() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTwelfthMarkSheetLink() {
        return twelfthMarkSheetLink;
    }

    public void setTwelfthMarkSheetLink(String twelfthMarkSheetLink) {
        this.twelfthMarkSheetLink = twelfthMarkSheetLink;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application [username=" + username + ", fullName=" + fullName + ", mobileNumber=" + mobileNumber
                + ", emailId=" + emailId + ", gender=" + gender + ", twelfthMarkSheetLink=" + twelfthMarkSheetLink
                + ", branch=" + branch + ", status=" + status + "]";
    }
}
