package com.rex.crm.common;

import com.google.gson.Gson;

public class Relation {
    private String name;
    private String display;
    private String from;
    private String to;
    private String sql;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, Relation.class);
    }

   
}
