package com.impaqgroup.training.logging.MDC;

import org.junit.Test;
import org.slf4j.*;

public class MdcExample {

    private final static Logger LOGGER = LoggerFactory.getLogger(MdcExample.class);

    @Test
    public void mdcContextExample(){
        LOGGER.info("Message without any data in context");
        MDC.put("user_name", "Budzigniew");
        LOGGER.debug("Data added to MDC context");
        LOGGER.info("Data still present in context");
        LOGGER.warn("Data will be removed from MDC context");
        MDC.remove("user_name");
        LOGGER.info("Data removed from context");
    }

}
