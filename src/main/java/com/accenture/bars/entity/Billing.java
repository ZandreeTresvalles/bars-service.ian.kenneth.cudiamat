package com.accenture.bars.entity;

import java.time.LocalDate;
import java.util.Set;
import com.accenture.bars.entity.Account;
public class Billing {
	private int billingId;
	private int billingCycle;
	private String billingMonth;
	private double amount;
	private LocalDate startDate;
	private LocalDate endDate;
	private String lastEdited;
	private Account account;

}
