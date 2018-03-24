package com.jnj.atm.services.entity.dto;

import com.jnj.atm.services.entity.AccountsDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jnj.atm.services.utility.Utilities;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement(name = "WithdrawResponse")
public class WithdrawResponse {

    @JsonProperty
    private int accountnumber;
    @JsonProperty
    private String customername;
    @JsonProperty
    private double withdrawAmount;
    @JsonProperty
    private double balance;
    @JsonProperty
    private Map noteCountMap;

    public WithdrawResponse(final AccountsDetail accountsDetail, final double withdrawAmount) {
        this.accountnumber = accountsDetail.getAccountnumber();
        this.customername = accountsDetail.getCustomername();
        this.withdrawAmount = withdrawAmount;
        this.balance = accountsDetail.getOpeningbal();
        this.noteCountMap = Utilities.dispencer((int)withdrawAmount);
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public String getCustomername() {
        return customername;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public double getBalance() {
        return balance;
    }

    public Map getNoteCountMap() {
        return noteCountMap;
    }
}
