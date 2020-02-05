package com.marcellus.testing.order;

import com.marcellus.testing.order.Order;
import com.marcellus.testing.order.OrderBackup;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreatingFileFirstShouldThroeException(){

        //given
        OrderBackup orderBackup = new OrderBackup();

        //when
        assertThrows(IOException.class, ()-> orderBackup.backupOrder(new Order()));
    }
}
