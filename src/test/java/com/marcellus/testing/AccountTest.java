package com.marcellus.testing;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void newAccountShouldNotBeActive(){
        //given
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(), "Check is new account is not active");
        assertThat(newAccount.isActive(), equalTo(false));
        assertThat(newAccount.isActive(), is(false));
    }
    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive(),equalTo(true));
    }
    @Test
    void newlyCreatedAccountShouldNotHaveDeliveryAddressSet() {
        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address,nullValue());
    }
    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){
        //given
        Address address = new Address("Krakowska","67C");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);
        //when
        Address address1 = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(address1);
        assertThat(address1,notNullValue());

    }
}
