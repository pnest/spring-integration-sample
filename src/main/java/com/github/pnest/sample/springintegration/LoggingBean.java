package com.github.pnest.sample.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class LoggingBean implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(LoggingBean.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Bean is initialized");
    }

    public void logMessage(Object message) {
        log.info(message.toString());
    }

}
