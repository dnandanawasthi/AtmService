package com.jnj.atm.services.resources;

import com.jnj.atm.services.entity.AccountsDetail;
import com.jnj.atm.services.entity.dto.BalanceResponse;
import com.jnj.atm.services.entity.dto.WithdrawResponse;
import com.jnj.atm.services.exceptions.AccountLowBalanceException;
import com.jnj.atm.services.exceptions.AmountNotValidException;
import com.jnj.atm.services.exceptions.AtmLowBalanceException;
import com.jnj.atm.services.exceptions.InputNotMatchException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IAtmServiceResource {

    BalanceResponse getBalance(@PathVariable("id") Integer id, @RequestParam int pin)
            throws InputNotMatchException;

    WithdrawResponse withdrawAmount(@PathVariable("id") Integer id, @RequestParam int pin,
                                    @RequestParam double withdrawAmount, @RequestParam int atmId)
            throws AccountLowBalanceException, InputNotMatchException, AmountNotValidException, AtmLowBalanceException;

    List<AccountsDetail> getAllAccounts();
}
