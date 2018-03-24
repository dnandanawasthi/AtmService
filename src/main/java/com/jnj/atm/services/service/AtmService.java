package com.jnj.atm.services.service;

import java.util.List;

import com.jnj.atm.services.entity.AccountsDetail;
import com.jnj.atm.services.entity.Atm;
import com.jnj.atm.services.entity.dto.BalanceResponse;
import com.jnj.atm.services.entity.dto.WithdrawResponse;
import com.jnj.atm.services.dao.IAtmServiceDao;
import com.jnj.atm.services.exceptions.AmountNotValidException;
import com.jnj.atm.services.exceptions.AtmLowBalanceException;
import com.jnj.atm.services.exceptions.InputNotMatchException;
import com.jnj.atm.services.exceptions.AccountLowBalanceException;
import com.jnj.atm.services.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmService implements IAtmService {
	@Autowired
	private IAtmServiceDao iAtmServiceDao;

	/**
	 * Method to fetch balance details
	 * @param accountNumber
	 * @param pin
	 * @return
	 * @throws InputNotMatchException
	 */
	@Override
	public BalanceResponse getBalance(int accountNumber, int pin) throws InputNotMatchException {
		AccountsDetail accountsDetail = iAtmServiceDao.balance(accountNumber, pin);
		BalanceResponse balanceResponse = new BalanceResponse(accountsDetail);
		return balanceResponse;
	}

	/**
	 * Method to withdraw requested amount, if account and atm has sufficient balance
	 * @param accountNumber
	 * @param pin
	 * @param withdrawlAmount
	 * @param atmId
	 * @return WithdrawResponse
	 * @throws AccountLowBalanceException
	 * @throws InputNotMatchException
	 * @throws AmountNotValidException
	 * @throws AtmLowBalanceException
	 */
	@Override
	public WithdrawResponse withdrawAmount(int accountNumber, int pin, double withdrawlAmount, int atmId)
			throws AccountLowBalanceException, InputNotMatchException, AmountNotValidException, AtmLowBalanceException {
		if (Utilities.isAmountValid(withdrawlAmount)) {
			throw new AmountNotValidException("Amount requested is not valid");
		}

		double atmBalance = atmBalance(atmId);

		if (atmBalance < withdrawlAmount) {
			throw new AtmLowBalanceException("Atm does not have sufficient balance");
		}

		iAtmServiceDao.widthdraw(accountNumber, pin, withdrawlAmount);

		iAtmServiceDao.updateAtmBalance(atmId, Utilities.dispencer((int)withdrawlAmount));

		AccountsDetail accountsDetail = iAtmServiceDao.balance(accountNumber, pin);
		WithdrawResponse withdrawResponse = new WithdrawResponse(accountsDetail, withdrawlAmount);
		return withdrawResponse;
	}

	/**
	 * Method to get total balance of ATM
	 * @param atmId
	 * @return
	 */
	public double atmBalance(int atmId) {
		Atm atm = iAtmServiceDao.atmBalance(atmId);
		double total = atm.getNote_5()*5;
		total = total + atm.getNote_10()*10;
		total = total + atm.getNote_20()*20;
		total = total + atm.getNote_50()*50;
		return total;
	}

	@Override
	public List<AccountsDetail> getAllAccountsDetail(){
		return iAtmServiceDao.getAllAccountsDetail();
	}

}
