package com.example.hopon;

public class sending {
    private String fromp;
    private String date;
    private String time;
    private String desc;
    private String top;
    private String pfp;
    private String name;
    private String upvot;
    private String mail;

     public sending(String fromp, String date, String time, String desc, String top, String pfp, String name,String upvot,String mail) {
        this.fromp = fromp;
        this.date = date;
        this.time = time;
        this.desc = desc;
        this.top = top;
        this.pfp=pfp;
        this.name=name;
        this.upvot=upvot;
        this.mail=mail;
    }

    public String getMail() {
        return mail;
    }

    public String getUpvot() {
        return upvot;
    }

    public String getNam() {
        return name;
    }

    public String getPfp() {
        return pfp;
    }

    public String getFromp() {
        return fromp;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDesc() {
        return desc;
    }

    public String getTop() {
        return top;
    }
}
