package com.jnj.atm.services.dao;
import java.util.List;
import java.util.Map;

import com.jnj.atm.services.entity.Atm;
import com.jnj.atm.services.entity.AccountsDetail;
import com.jnj.atm.services.exceptions.AtmLowBalanceException;
import com.jnj.atm.services.exceptions.InputNotMatchException;
import com.jnj.atm.services.exceptions.AccountLowBalanceException;

public interface IAtmServiceDao {
    List<AccountsDetail> getAllAccountsDetail();
    AccountsDetail balance(int accountNumber, int pin) throws InputNotMatchException;
    void widthdraw(int accountnumber, int pin, double requestedAmount) throws AccountLowBalanceException, InputNotMatchException;
    Atm atmBalance(int atmId);
    void updateAtmBalance(int atmId, Map<String, Integer> notesMap) throws AtmLowBalanceException;
}
 