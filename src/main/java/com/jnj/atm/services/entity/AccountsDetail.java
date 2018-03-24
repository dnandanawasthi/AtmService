package com.jnj.atm.services.entity;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="atm_customer_details")
public class AccountsDetail implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="accountnumber")
    private int accountnumber;
	@NotNull
	@Column(name="customername")
    private String customername;
	@Column(name="openingbal")
	private double openingbal;
	@Column(name="overdraft")
	private double overdraft;
	@Column(name="pin")
	private int pin;

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public double getOpeningbal() {
		return openingbal;
	}

	public void setOpeningbal(double openingbal) {
		this.openingbal = openingbal;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "AccountsDetail{" +
				"accountnumber=" + accountnumber +
				", customername='" + customername + '\'' +
				", openingbal=" + openingbal +
				", overdraft=" + overdraft +
				", pin=" + pin +
				'}';
	}
}