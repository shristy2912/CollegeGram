package com.codewithshristy.collegegram;

public class Admins {

    private String Uid;
    private String Name;
    private String Department;
    private String Email;
    private String Password;
    private int userType;

    public Admins(){}

    public Admins(String uid, String name, String department, String email, String password, int userType) {
        Uid = uid;
        Name = name;
        Department = department;
        Email = email;
        Password = password;
        this.userType = userType;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
