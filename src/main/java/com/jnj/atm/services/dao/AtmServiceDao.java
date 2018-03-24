package com.jnj.atm.services.dao;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.jnj.atm.services.entity.AccountsDetail;
import com.jnj.atm.services.entity.Atm;
import com.jnj.atm.services.exceptions.AtmLowBalanceException;
import com.jnj.atm.services.exceptions.InputNotMatchException;
import com.jnj.atm.services.exceptions.AccountLowBalanceException;
import com.jnj.atm.services.utility.Utilities;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class AtmServiceDao implements IAtmServiceDao {

	@PersistenceContext	
	private EntityManager entityManager;

	/**
	 * Method fetch account details on match of pin
	 * @param accountNumber
	 * @param pin
	 * @return AccountsDetail
	 * @throws InputNotMatchException
	 */
	@Override
	public AccountsDetail balance(int accountNumber, int pin) throws InputNotMatchException {
		String sql = "FROM AccountsDetail WHERE pin="+pin+" AND accountnumber="+ accountNumber;
		AccountsDetail accountsDetail = null;
		try {
			accountsDetail = (AccountsDetail) entityManager.createQuery(sql).getSingleResult();
		} catch(NoResultException inme) {
			throw new InputNotMatchException("Input provided for "+ accountNumber +" does not match with records");
		}
		return accountsDetail;
	}

	/**
	 * Method allow to get the amount , if input is correct and atm and account has sufficient balance
	 * @param accountnumber
	 * @param pin
	 * @param requestedAmount
	 * @throws AccountLowBalanceException
	 * @throws InputNotMatchException
	 */
	@Override
	public void widthdraw(int accountnumber, int pin, double requestedAmount) throws AccountLowBalanceException, InputNotMatchException {

		AccountsDetail accountsDetail = balance(accountnumber, pin);
		double balance = accountsDetail.getOpeningbal();
		if(balance > 0 && Utilities.validateLimit(requestedAmount, balance)) {
			accountsDetail.setOpeningbal(accountsDetail.getOpeningbal() - requestedAmount);
			entityManager.flush();
		} else {
			throw new AccountLowBalanceException(accountnumber+" does not have required balance");
		}
	}

	/**
	 * Method validate the ATM notes balance against withdraw
	 * @param atmId
	 * @param notesMap
	 * @throws AtmLowBalanceException
	 */
	@Override
	public void updateAtmBalance(int atmId, Map<String, Integer> notesMap) throws AtmLowBalanceException {

		Atm atm = atmBalance(atmId);

		for (Map.Entry<String, Integer> entry : notesMap.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			if(key.contains("50") && value <= atm.getNote_50()) {
				atm.setNote_50(atm.getNote_50()- value);
			} else if(key.contains("20") && value <= atm.getNote_20()) {
				atm.setNote_20(atm.getNote_20()- value);
			} else if(key.contains("10") && value <= atm.getNote_10()) {
				atm.setNote_10(atm.getNote_10()- value);
			} else if(key.contains("5") && value <= atm.getNote_5()) {
				atm.setNote_5(atm.getNote_5()- value);
			} else {
				throw new AtmLowBalanceException("Atm does not have sufficient notes");
			}
		}

		entityManager.flush();
	}

	/**
	 * Method fetch ATM object to get detailed notes information
	 * @param atmId
	 * @return Atm
	 */
	@Override
	public Atm atmBalance(int atmId) {
		return entityManager.find(Atm.class, atmId);
	}


	/**
	 * Method get the List of All accounts
	 * @return
	 */
	@Override
	public List<AccountsDetail> getAllAccountsDetail() {
		String hql = "FROM AccountsDetail as atcl ORDER BY atcl.accountnumber";
		return (List<AccountsDetail>) entityManager.createQuery(hql).getResultList();
	}	
}
