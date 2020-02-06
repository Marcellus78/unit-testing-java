package com.marcellus.testing;

import com.marcellus.testing.extension.IAExceptionIgnoreExtension;
import com.marcellus.testing.order.Order;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class MealTest {

    @Spy
    private Meal mealSpy;

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
    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {1,3,5,8})
    void mealPricesShouldBeLowerThanTen(int price){
        if(price>5){
            throw new IllegalArgumentException();
        }
        assertThat(price, lessThan(20));
    }

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){

        Order order = new Order();
        order.addMealToOrder(new Meal(10, 2,"Hamburger"));
        order.addMealToOrder(new Meal(7, 4,"Fries"));
        order.addMealToOrder(new Meal(22, 3,"Pizzar"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for(int i=0;i<order.getMeals().size();i++){

            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = ()-> {
                assertThat(calculatePrice(price,quantity), lessThan(67));
            };
            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name,executable);
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }

    @Test
    void testMealSumPrice(){
        //given
        Meal meal = mock(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);

        given(meal.sumPrice()).willCallRealMethod();

        //when
        int sumPrice = meal.sumPrice();

        //then
        assertThat(sumPrice, equalTo(45));
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void testMealSumPriceWithSpy(){
        //given

        given(mealSpy.getPrice()).willReturn(15);
        given(mealSpy.getQuantity()).willReturn(3);

        //when
        int sumPrice = mealSpy.sumPrice();

        //then
        then(mealSpy).should().getPrice();
        then(mealSpy).should().getQuantity();
        assertThat(sumPrice, equalTo(45));
    }
    private int calculatePrice(int price, int quantity){
        return price*quantity;
    }
}