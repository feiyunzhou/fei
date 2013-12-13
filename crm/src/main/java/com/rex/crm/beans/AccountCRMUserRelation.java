package com.rex.crm.beans;

public class AccountCRMUserRelation {
    private int accountId;
    private int crmuserId;
    private int id;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

	public int getCrmuserId() {
		return crmuserId;
	}

	public void setCrmuserId(int crmuserId) {
		this.crmuserId = crmuserId;
	}

}
