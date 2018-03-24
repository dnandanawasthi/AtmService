package com.jnj.atm.services.resources;

import java.util.List;

import com.jnj.atm.services.entity.AccountsDetail;
import com.jnj.atm.services.entity.dto.BalanceResponse;
import com.jnj.atm.services.entity.dto.WithdrawResponse;
import com.jnj.atm.services.exceptions.AmountNotValidException;
import com.jnj.atm.services.exceptions.AtmLowBalanceException;
import com.jnj.atm.services.exceptions.InputNotMatchException;
import com.jnj.atm.services.exceptions.AccountLowBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jnj.atm.services.service.IAtmService;

@RestController
@RequestMapping("customer")
public class AtmServiceResource implements IAtmServiceResource {
	@Autowired
	private IAtmService iAtmService;

	/**
	 * Service fetch balance, if provided pin is correct
	 * @param id
	 * @param pin
	 * @return ResponseEntity<BalanceResponse>
	 * @throws InputNotMatchException
	 */
	@Override
	@GetMapping(path =  "balance/{id}", produces = "application/json;charset=UTF-8")
	public BalanceResponse getBalance(@PathVariable("id") Integer id, @RequestParam int pin)
			throws InputNotMatchException {
		BalanceResponse balanceResponse = iAtmService.getBalance(id, pin);
		return balanceResponse;
	}

	/**
	 * Service withdraw amount, if provided pin is correct
	 * Service is capable for dispensing different type of notes, based on amount
	 * Service is capable to restrict withdraw, if account or atm has no enough balance
	 * Service is capable to restrict withdraw, if amount requested which need notes of less than 5
	 * @param id
	 * @param pin
	 * @param withdrawAmount
	 * @param atmId
	 * @return ResponseEntity<WithdrawResponse>
	 * @throws AccountLowBalanceException
	 * @throws InputNotMatchException
	 * @throws AmountNotValidException
	 * @throws AtmLowBalanceException
	 */
	@Override
	@GetMapping(path =  "withdraw/{id}", produces = "application/json;charset=UTF-8")
	public WithdrawResponse withdrawAmount(@PathVariable("id") Integer id, @RequestParam int pin,
														   @RequestParam double withdrawAmount, @RequestParam int atmId)
			throws AccountLowBalanceException, InputNotMatchException, AmountNotValidException, AtmLowBalanceException {
		WithdrawResponse withdrawResponse = null;
		try {
			withdrawResponse = iAtmService.withdrawAmount(id, pin, withdrawAmount, atmId);
		} catch (AccountLowBalanceException | AmountNotValidException |
				InputNotMatchException | AtmLowBalanceException e) {
			throw e;
		}
		return withdrawResponse;
	}

	/**
	 * Service is capable of responding all the accounts
	 * @return ResponseEntity<List<AccountsDetail>>
	 */
	@Override
	@GetMapping("accounts")
	public List<AccountsDetail> getAllAccounts() {
		List<AccountsDetail> list = iAtmService.getAllAccountsDetail();
		return list;
	}

} 