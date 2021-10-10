package com.example.covidtracker;

import android.widget.ImageView;

public class AboutCovid {

    private String cnt;
    private String content;
    private String heading;

    public AboutCovid() {
        cnt = "";
        content = "";
        heading = "";
    }

    public AboutCovid(String cnt, String content, String heading) {
        this.cnt = cnt;
        this.content = content;
        this.heading = heading;
    }


    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
