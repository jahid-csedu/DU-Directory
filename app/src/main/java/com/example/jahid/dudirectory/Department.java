package com.example.jahid.dudirectory;

/**
 * Created by jahid on 4/16/2017.
 */

public class Department {
    String departmentName,email;

    public Department(String departmentName, String email){
        setDepartmentName(departmentName);
        setEmail(email);
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;

    }

    public String getEmail() {
        return email;
    }

}
