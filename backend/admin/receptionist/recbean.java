package com.example.HAD.admin.receptionist;


import com.example.HAD.login.bean.LoginBean;

import javax.persistence.*;


@Entity
public class recbean {



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


    private String name;

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
   public String hos_id;

    String email_Id;


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



    String YearofBirth;

 @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn(name = "fk_recid",referencedColumnName = "id")
 private LoginBean login;


    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
}
