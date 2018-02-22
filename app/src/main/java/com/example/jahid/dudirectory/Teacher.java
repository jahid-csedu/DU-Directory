package com.example.jahid.dudirectory;

/**
 * Created by jahid on 4/23/2017.
 */

public class Teacher {
    String name,email,email2,phone,phone2,designation,department;

    public Teacher(String name,String designation, String phone,String phone2, String email, String email2,String department){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.phone2 = phone2;
        this.email2 = email2;
        this.designation = designation;
        this.department = department;
    }

    public Teacher(String name,String designation, String phone, String email,String department){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.designation = designation;
        this.department = department;
    }

    public String getEmail2() {
        return email2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }

    public String getDesignation(){
        return designation;
    }
}
