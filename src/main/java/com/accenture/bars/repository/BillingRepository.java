package com.accenture.bars.repository;

import java.time.LocalDate;
import com.accenture.bars.entity.Billing;

public class BillingRepository {
	
	private int billingId;
	private	int billingCycle;
	private	String billingMonth;
	private Double amount;
	private LocalDate startDate;
	private LocalDate endDate;
	private String lastEdited;
	public BillingRepository() {
		super();
	}
	public Billing findByBillingCycleAndStartDateAndEndDate(int billingCycle, LocalDate startDate, LocalDate endDate) {
		
		return null;
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
