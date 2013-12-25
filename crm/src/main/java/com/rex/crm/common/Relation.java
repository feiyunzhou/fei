package com.rex.crm.common;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;

public class Relation implements Serializable{
    private String name;
    private String display;
    private String from;
    private String to;
    private String sql;
    private String sql_admin;
    private String sql_manager;
    private List<Reaction> reactions;
    
    public String getSql_admin() {
		return sql_admin;
	}

	public void setSql_admin(String sql_admin) {
		this.sql_admin = sql_admin;
	}

	public String getSql_manager() {
		return sql_manager;
	}

	public void setSql_manager(String sql_manager) {
		this.sql_manager = sql_manager;
	}

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

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

   
}
