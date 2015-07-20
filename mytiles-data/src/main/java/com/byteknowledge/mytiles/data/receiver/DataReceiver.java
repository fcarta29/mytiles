package com.byteknowledge.mytiles.data.receiver;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DataReceiver {
    
    private static final Logger LOGGER = Logger.getLogger(DataReceiver.class);

    private CountDownLatch latch;

    @Autowired
    public DataReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        latch.countDown();
    }
}
