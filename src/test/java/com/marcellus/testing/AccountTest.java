package com.marcellus.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    @Test
    void newAccountShouldNotBeActive(){
        //given
        //when
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(), "Check is new account is not active");
    }
    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());
    }
}
