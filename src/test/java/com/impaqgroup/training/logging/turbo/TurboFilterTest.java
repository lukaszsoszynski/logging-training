package com.impaqgroup.training.logging.turbo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TurboFilterTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TurboFilterTest.class);

    @Test
    public void turboFilterTest(){
        LOGGER.trace("{} is a very nice name", "Dobromysław");
        LOGGER.trace("{} is a very nice name", "Łukasz");
        LOGGER.trace("{} is a very nice name", "Kociebor");
    }

}
