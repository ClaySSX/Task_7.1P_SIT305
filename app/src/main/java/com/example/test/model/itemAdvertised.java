package com.example.test.model;

public class itemAdvertised {

    private int advert_ID;
    private String usersName;
    private String mobileNum;
    private String itemName;
    private String itemDate;
    private String itemLocation;

    public itemAdvertised(String usersName, String mobileNum, String itemName, String itemDate, String itemLocation) {
        this.usersName = usersName;
        this.mobileNum = mobileNum;
        this.itemName = itemName;
        this.itemDate = itemDate;
        this.itemLocation = itemLocation;
    }

    public itemAdvertised() {
    }

    public int getAdvert_ID() {
        return advert_ID;
    }

    public void setAdvert_ID(int advert_ID) {
        this.advert_ID = advert_ID;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }
}