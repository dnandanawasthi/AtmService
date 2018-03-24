package com.jnj.atm.services;

import com.jnj.atm.services.entity.AccountsDetail;
import com.jnj.atm.services.resources.AtmServiceResource;
import com.jnj.atm.services.entity.dto.BalanceResponse;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@WebMvcTest(AtmServiceResource.class)
public class AtmServiceResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AtmServiceResource atmServiceResource;

    @Test
    public void getAllAccounts() throws Exception {
        String URL = "/customer/accounts";
        AccountsDetail accountsDetail = new AccountsDetail();
        accountsDetail.setAccountnumber(123456789);
        accountsDetail.setPin(1234);

        List<AccountsDetail> allAccounts = singletonList(accountsDetail);
        given(atmServiceResource.getAllAccounts()).willReturn(allAccounts);

        mvc.perform(get(URL).contentType(APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$[0].accountnumber", is(accountsDetail.getAccountnumber())));
    }

    @Test
    public void getBalance() throws Exception {
        String URL = "/customer/balance/123456789?pin=1234";
        AccountsDetail accountsDetail = new AccountsDetail();
        accountsDetail.setAccountnumber(123456789);
        accountsDetail.setPin(1234);
        accountsDetail.setOpeningbal(20000000.22);

        BalanceResponse balanceResponse = new BalanceResponse(accountsDetail);

        given(atmServiceResource.getBalance(accountsDetail.getAccountnumber(), accountsDetail.getPin())).willReturn(balanceResponse);

        mvc.perform(get(URL).contentType(APPLICATION_JSON)).andExpect(status().isOk()).
                andExpect(jsonPath("$.accountnumber", is(accountsDetail.getAccountnumber()))).
                andExpect(jsonPath("$.balance", is(2.000000022E7)));

    }

}
