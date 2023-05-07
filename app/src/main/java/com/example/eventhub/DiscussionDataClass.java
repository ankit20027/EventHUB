package com.example.eventhub;

import java.io.Serializable;

public class DiscussionDataClass {
    private String email;
    private String txt;

    public DiscussionDataClass() {

    }

    public DiscussionDataClass(String email, String txt) {
        this.email = email;
        this.txt = txt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
