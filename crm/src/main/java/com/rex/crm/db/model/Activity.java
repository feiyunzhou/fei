package com.rex.crm.db.model;

import java.util.Date;

// Generated 2013-5-4 16:44:24 by Hibernate Tools 4.0.0

/**
 * Activity generated by hbm2java
 */
public class Activity implements java.io.Serializable {

	private Integer id;
	private int crmuserId;
	private Date endtime;
	private Date starttime;
	private String title;
    private int event_type;
    private int status;
    private int activity_coachType;
	public Activity() {
	}

	public Activity(int crmuserId, Date starttime) {
		this.crmuserId = crmuserId;
		this.starttime = starttime;
	}

	public Activity(int crmuserId, Date endtime, Date starttime, String title) {
		this.crmuserId = crmuserId;
		this.endtime = endtime;
		this.starttime = starttime;
		this.title = title;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCrmuserId() {
		return this.crmuserId;
	}

	public void setCrmuserId(int crmuserId) {
		this.crmuserId = crmuserId;
	}

	
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEvent_type() {
		return event_type;
	}

	public void setEvent_type(int event_type) {
		this.event_type = event_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getActivity_coachType() {
		return activity_coachType;
	}

	public void setActivity_coachType(int activity_coachType) {
		this.activity_coachType = activity_coachType;
	}
	
}
