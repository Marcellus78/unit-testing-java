package com.marcellus.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
    @ParameterizedTest
    @ValueSource(ints = {5,10,15,18})
    void mealPricesShouldBeLowerThanTwenty(int price){
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNamesAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    @ParameterizedTest
    @MethodSource("cakeNames")
    void cakesNamesShouldEndWithCake(String name){
        assertThat(name, endsWith("cake"));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
            Arguments.of("Hamburger", 10),
            Arguments.of("Cheeseburger",12)
        );
    }

    private static Stream<String> cakeNames(){
        List<String> cakes = Arrays.asList("cheesecake", "fruitcake", "cupcake");
        return cakes.stream();
    }

}