package com.marcellus.testing;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28,discountedPrice);
        assertThat(discountedPrice,equalTo(28));
    }
    @Test
    void referenceToTheSameObjectShouldBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1,meal2);
        assertThat(meal1,sameInstance(meal2));
    }
    @Test
    void referenceToDifferentObjectShouldNotBeEqual(){

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        //then
        assertNotEquals(meal1,meal2);
        assertThat(meal1,not(sameInstance(meal2)));
    }
    @Test
    void twoMealsShouldBeEqualsWhenPriceAndNameAreTheSame(){

        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");

        //then
        assertEquals(meal1,meal2);

    }
    @Test
    void exceptionShouldBeThrownIsDicountHigherThanPrice(){

        //given
        Meal meal = new Meal(8,"zupa");

        //when
        //then
        assertThrows(IllegalArgumentException.class,()->meal.getDiscountedPrice(10));
    }
}