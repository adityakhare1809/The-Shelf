package com.aditya.theshelf;

public class bookdata {

    String user;
    String condition;
    String subject;
    String originalprice;
    String sellingprice;
    String description;
    String fullname;
    String imageid;

    public String getUrl() {
        return url;
    }

    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getNumber() {
        return number;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    String number;
    String emailaddress;

    public String getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(String originalprice) {
        this.originalprice = originalprice;
    }

    public void setSellingprice(String sellingprice) {
        this.sellingprice = sellingprice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellingprice() {
        return sellingprice;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }



    public String getCondition() {
        return condition;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public bookdata()
    {

    }
}
