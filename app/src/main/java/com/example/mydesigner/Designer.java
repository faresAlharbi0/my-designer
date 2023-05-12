package com.example.mydesigner;

public class Designer {
    public String fullName, phoneNumber, email, address, specialization, company, briefYourProjects;
    public Designer(String fullName, String phone,String email, String address,String specialization, String company, String projects){
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber= phone;
        this.address = address;
        this.specialization = specialization;
        this.company = company;
        this.briefYourProjects = projects;
    }
}