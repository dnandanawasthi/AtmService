package com.jnj.atm.services.entity.dto;

import com.jnj.atm.services.entity.AccountsDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jnj.atm.services.utility.Utilities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BalanceResponse")
public class BalanceResponse {

    @JsonProperty
    private int accountnumber;
    @JsonProperty
    private String customername;
    @JsonProperty
    private double balance;
    @JsonProperty
    private double withdralLimit;

    public BalanceResponse(final AccountsDetail accountsDetail) {
        this.accountnumber = accountsDetail.getAccountnumber();
        this.customername = accountsDetail.getCustomername();
        this.balance = accountsDetail.getOpeningbal();
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public String getCustomername() {
        return customername;
    }

    public double getBalance() {
        return balance;
    }

    public double getWithdralLimit() {
        return Utilities.calculateLimit(balance);
    }
}
