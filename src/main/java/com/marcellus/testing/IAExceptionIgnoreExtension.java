package com.marcellus.testing;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

public class IAExceptionIgnoreExtension implements TestExecutionExceptionHandler {

    private final static Logger LOGGER = Logger.getLogger(IAExceptionIgnoreExtension.class.getName());

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {

        if(throwable instanceof IllegalArgumentException){
            LOGGER.info("just ignore IllegalArgumentException!");
        }else{
            throw throwable;
        }
    }
}
