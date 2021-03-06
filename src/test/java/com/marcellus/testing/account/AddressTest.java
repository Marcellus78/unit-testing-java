package com.marcellus.testing.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10","Armii Krajowej, 57/11", "'Romka, Tomka, Atomka', 40"})
    void givenAddresesShouldNotBeEmptyAndHaveProperNames(String street, String number){

        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
        assertThat(number.length(), greaterThanOrEqualTo(1));
    }
    @ParameterizedTest
    @CsvFileSource(resources="/addreses.csv")
    void addresesFromFileShouldNotBeEmptyAndHaveProperNames(String street, String number){

        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
        assertThat(number.length(), greaterThanOrEqualTo(1));
    }
}
