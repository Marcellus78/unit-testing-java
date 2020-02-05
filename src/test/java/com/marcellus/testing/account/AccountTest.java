package com.marcellus.testing.account;

import com.marcellus.testing.account.Account;
import com.marcellus.testing.account.Address;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

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
    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldBeActive(){

        //given
        Address address = new Address("Puławsk","46/6");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, ()->{
            assertTrue(account.isActive());
        });
    }
    @Test
    void invalidEmailShouldThrowException(){
        //given
        Account account = new Account();

        //when
        //then
        assertThrows(IllegalArgumentException.class,()->account.setEmail("wrongEmail"));
    }
    @Test
    void emailCorrectEmailShouldBeAdded(){
        //given
        Account account = new Account();

        //when
        account.setEmail("moniuszko@gmail.com");

        //then
        assertThat(account.getEmail(), is("moniuszko@gmail.com"));
    }

}
