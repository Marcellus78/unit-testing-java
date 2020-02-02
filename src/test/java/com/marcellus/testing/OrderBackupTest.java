package com.marcellus.testing;

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


    @Test
    void backupOrderwithOneMeal() throws IOException {

        //given
        Meal meal = new Meal(7,"Fries");
        Order order = new Order();
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
