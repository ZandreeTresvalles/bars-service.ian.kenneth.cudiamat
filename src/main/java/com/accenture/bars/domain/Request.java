package com.accenture.bars.domain;

import java.time.LocalDate;

public class Request {
	private int billingId;
	private	int billingCycle;
	private	String billingMonth;
	private Double amount;
	private LocalDate startDate;
	private LocalDate endDate;
	private String lastEdited;
	
	//private
	
	public Request() {
		
	}
	public Request(int billingCycle, LocalDate startDate, LocalDate endDate) {
		
	}
	public int getBillingId() {
		return billingId;
	}
	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}
	public int getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}
	public String getBillingMonth() {
		return billingMonth;
	}
	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getLastEdited() {
		return lastEdited;
	}
	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}
	
	
}
