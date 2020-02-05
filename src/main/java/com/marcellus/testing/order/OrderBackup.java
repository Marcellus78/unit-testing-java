package com.marcellus.testing.order;

import com.marcellus.testing.order.Order;

import java.io.*;

public class OrderBackup {

    private Writer writer;

    public Writer getWriter() {
        return writer;
    }
    public void createFile() throws FileNotFoundException {

        File file = new File("orderbackup.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        writer = new BufferedWriter(outputStreamWriter);
    }

    public void backupOrder(Order order) throws IOException {
        if(writer == null){
            throw new IOException("Backup file not cretated");
        }
        writer.append(order.toString());
    }
    public void closeFile() throws IOException {
        writer.close();
    }
}
