package com.jnj.atm.services;

import com.jnj.atm.services.resources.AtmServiceResource;
import com.jnj.atm.services.entity.dto.BalanceResponse;
import com.jnj.atm.services.entity.dto.WithdrawResponse;
import com.jnj.atm.services.exceptions.AccountLowBalanceException;
import com.jnj.atm.services.exceptions.AmountNotValidException;
import com.jnj.atm.services.exceptions.AtmLowBalanceException;
import com.jnj.atm.services.exceptions.InputNotMatchException;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AtmServiceApplication.class)
public class AtmServiceResourceAutomatedTest {

    private final int accountNumber = 123456789;
    private final int pin = 1234;
    private final int pin_incorrect = 1235;

    @Autowired
    private AtmServiceResource atmServiceResource;

    @Test
    public void balanceTest() throws InputNotMatchException {
        BalanceResponse balanceResponse = atmServiceResource.getBalance(123456789, pin);

        Assert.assertEquals("Balance does not match ", 20000.00, balanceResponse.getBalance(), 0.00);
        Assert.assertEquals("Name does not match", "Robert", balanceResponse.getCustomername());

    }

    @Test
    public void  withdralTest() throws AccountLowBalanceException, InputNotMatchException, AmountNotValidException, AtmLowBalanceException{
        WithdrawResponse withdrawResponse = atmServiceResource.withdrawAmount(accountNumber, pin, 500, 1001);

        Assert.assertEquals("Balance does not match ", 19500.00, withdrawResponse.getBalance(), 0.00);
        Assert.assertEquals("Name does not match", "Robert", withdrawResponse.getCustomername());

        withdrawResponse = atmServiceResource.withdrawAmount(accountNumber, pin, 500, 1001);

        Assert.assertEquals("Balance does not match ", 19000.00, withdrawResponse.getBalance(), 0.00);

        try {
            atmServiceResource.withdrawAmount(accountNumber, pin, 1000, 1001);
        } catch (AtmLowBalanceException e) {
            Assert.assertEquals("Atm does not have sufficient notes", e.getMessage());
        }

    }

    @Test
    public void pinNotCorrectTest() throws AccountLowBalanceException, InputNotMatchException, AmountNotValidException, AtmLowBalanceException{
        try {
            BalanceResponse balanceResponse = atmServiceResource.getBalance(accountNumber, pin_incorrect);
        } catch(InputNotMatchException e) {
            Assert.assertEquals("Input provided for "+ accountNumber +" does not match with records", e.getMessage());
        }

    }

    @Test
    public void accountDoesNotEnoughBalanceTest() throws AccountLowBalanceException, InputNotMatchException, AmountNotValidException, AtmLowBalanceException{

        try {
            WithdrawResponse withdrawResponse = atmServiceResource.withdrawAmount(111111111, 1111, 800, 1003);
        } catch (AccountLowBalanceException e) {
            Assert.assertEquals("111111111 does not have required balance", e.getMessage());
        }


    }
}
