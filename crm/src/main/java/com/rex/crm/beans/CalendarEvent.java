package com.rex.crm.beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CalendarEvent {
	private int id;
	private String title;
	private boolean allDay = false;
	private String start;
    private String end;
    private long starttime;
    private long endtime;
	private int crmUserId;
	private int activity_type;
	private int contactId;
	private int status;
	private String contactIds;
	private int crmUserManagerId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this, CalendarEvent.class).toString();
		
	}

	public int getCrmUserId() {
		return crmUserId;
	}

	public void setCrmUserId(int crmUserId) {
		this.crmUserId = crmUserId;
	}
	
	    public String getStart() {
	        return start;
	    }

	    public void setStart(String start) {
	        this.start = start;
	    }

	    public String getEnd() {
	        return end;
	    }

	    public void setEnd(String end) {
	        this.end = end;
	    }

        public long getStarttime() {
            return starttime;
        }

        public void setStarttime(long starttime) {
            this.starttime = starttime;
        }

        public long getEndtime() {
            return endtime;
        }

        public void setEndtime(long endtime) {
            this.endtime = endtime;
        }

        public int getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(int activity_type) {
            this.activity_type = activity_type;
        }

        public int getContactId() {
            return contactId;
        }

        public void setContactId(int contactId) {
            this.contactId = contactId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getContactIds() {
            return contactIds;
        }

        public void setContactIds(String contactIds) {
            this.contactIds = contactIds;
        }

        public int getCrmUserManagerId() {
            return crmUserManagerId;
        }

        public void setCrmUserManagerId(int crmUserManagerId) {
            this.crmUserManagerId = crmUserManagerId;
        }

}
