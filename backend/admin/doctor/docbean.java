package com.example.HAD.admin.doctor;


import com.example.HAD.login.bean.LoginBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity

public class docbean {



   private String mobile;

   private String address;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbha_id() {
        return abha_id;
    }

    public void setAbha_id(String abha_id) {
        this.abha_id = abha_id;
    }

    private String name;

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public String getHos_id() {
        return hos_id;
    }

    public void setHos_id(String hos_id) {
        this.hos_id = hos_id;
    }

    public String getEmail_Id() {
        return email_Id;
    }

    public void setEmail_Id(String email_Id) {
        this.email_Id = email_Id;
    }

    @Id
    @Column(name = "hos_id")
   private String hos_id;

    String email_Id;
    
    String abha_id;

    String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYearofBirth() {
        return YearofBirth;
    }

    public void setYearofBirth(String yearofBirth) {
        YearofBirth = yearofBirth;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getSpecility() {
        return specility;
    }

    public void setSpecility(String specility) {
        this.specility = specility;
    }

    String YearofBirth;

    String Country;

    String specility;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id",referencedColumnName = "id")
    private LoginBean login;




}
