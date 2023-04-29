package com.codewithshristy.collegegram;

public class Students {

    private String Uid;
    private String Name;
    private String branch;
    private String year;
    private String regNo;
    private String email;
    private String password;
    private int userType;

    public Students(){}

    public Students(String uid,String name, String branch, String year, String regNo, String email, String password,int userType) {
        Uid = uid;
        Name = name;
        this.branch = branch;
        this.year = year;
        this.regNo = regNo;
        this.email = email;
        this.password = password;
        userType = userType;
    }


    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
