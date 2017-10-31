package com.ruslanlyalko.agency.data.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ruslan Lyalko
 * on 30.10.2017.
 */
public class UserItem implements Serializable {
    private String Id;
    private String Name;
    private String Role;
    private String Avatar;
    private Integer Rate;
    private Date StartDate;
    private Date BdayDate;
    private String Phone;
    private String Email;
    private boolean IsAdmin;

    public UserItem() {
        //required
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public Integer getRate() {
        return Rate;
    }

    public void setRate(Integer rate) {
        Rate = rate;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getBdayDate() {
        return BdayDate;
    }

    public void setBdayDate(Date bdayDate) {
        BdayDate = bdayDate;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(boolean admin) {
        IsAdmin = admin;
    }
}