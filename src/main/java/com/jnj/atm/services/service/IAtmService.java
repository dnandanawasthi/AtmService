package com.jnj.atm.services.service;

import java.util.List;

import com.jnj.atm.services.entity.AccountsDetail;
import com.jnj.atm.services.entity.dto.BalanceResponse;
import com.jnj.atm.services.entity.dto.WithdrawResponse;
import com.jnj.atm.services.exceptions.AmountNotValidException;
import com.jnj.atm.services.exceptions.AtmLowBalanceException;
import com.jnj.atm.services.exceptions.InputNotMatchException;
import com.jnj.atm.services.exceptions.AccountLowBalanceException;

public interface IAtmService {
     List<AccountsDetail> getAllAccountsDetail();
     BalanceResponse getBalance(int accountNumber, int pin) throws InputNotMatchException;
     WithdrawResponse withdrawAmount(int accountNumber, int pin, double withdrawlAmount, int atmId)
             throws AccountLowBalanceException, InputNotMatchException, AmountNotValidException, AtmLowBalanceException;
     double atmBalance(int atmId);
}
