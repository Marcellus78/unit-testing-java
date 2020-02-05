package com.marcellus.testing.order;

import com.marcellus.testing.Meal;
import com.marcellus.testing.order.Order;
import com.marcellus.testing.order.OrderBackup;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void appendAtTheStartOfTheLine() throws IOException {
        orderBackup.getWriter().append("New order: ");
    }

    @AfterEach
    void appendAtTheEndOfTheLine() throws IOException {
        orderBackup.getWriter().append(" backed up.");
    }


    @Tag("fries")
    @Test
    void backupOrderWithOneMeal() throws IOException {

        //given
        Meal meal = new Meal(7,"Fries");
        com.marcellus.testing.order.Order order = new Order();
        order.addMealToOrder(meal);

        //when
        orderBackup.backupOrder(order);

        //then
        System.out.println("Order " + order.toString() + " backed up.");

    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }
}
