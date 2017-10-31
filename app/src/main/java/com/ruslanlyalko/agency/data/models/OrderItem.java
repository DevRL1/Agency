package com.ruslanlyalko.agency.data.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ruslan Lyalko
 * on 30.10.2017.
 */

public class OrderItem implements Serializable {
    private String Id;
    private String Status;
    private String ClientName;
    private String ClientPhone;
    private String Description;
    private String AnimatorId1;
    private String AnimatorName1;
    private String AnimatorId2;
    private String AnimatorName2;
    private String CreatedBy;
    private int Children;
    private int Rate;
    private float Hours;
    private float Incomings;
    private float Outgoigs;
    private Date OrderDate;
    private Date CreatedDate;
    private Date UpdatedDate;

    public String getAnimatorName1() {
        return AnimatorName1;
    }

    public void setAnimatorName1(String animatorName1) {
        AnimatorName1 = animatorName1;
    }

    public String getAnimatorName2() {
        return AnimatorName2;
    }

    public void setAnimatorName2(String animatorName2) {
        AnimatorName2 = animatorName2;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getClientPhone() {
        return ClientPhone;
    }

    public void setClientPhone(String clientPhone) {
        ClientPhone = clientPhone;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAnimatorId1() {
        return AnimatorId1;
    }

    public void setAnimatorId1(String animatorId1) {
        AnimatorId1 = animatorId1;
    }

    public String getAnimatorId2() {
        return AnimatorId2;
    }

    public void setAnimatorId2(String animatorId2) {
        AnimatorId2 = animatorId2;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public int getChildren() {
        return Children;
    }

    public void setChildren(int children) {
        Children = children;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public float getHours() {
        return Hours;
    }

    public void setHours(float hours) {
        Hours = hours;
    }

    public float getIncomings() {
        return Incomings;
    }

    public void setIncomings(float incomings) {
        Incomings = incomings;
    }

    public float getOutgoigs() {
        return Outgoigs;
    }

    public void setOutgoigs(float outgoigs) {
        Outgoigs = outgoigs;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        UpdatedDate = updatedDate;
    }
}
