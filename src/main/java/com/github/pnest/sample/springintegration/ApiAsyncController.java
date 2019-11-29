package com.github.pnest.sample.springintegration;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api")
class ApiAsyncController {

    private static final Logger log = LoggerFactory.getLogger(ApiAsyncController.class);

    @Autowired
    private MessageChannel queueChannel;

    public void setQueueChannel(MessageChannel queueChannel) {
        this.queueChannel = queueChannel;
    }

    @RequestMapping(value = "/queue", method = RequestMethod.POST)
    public Callable<ResponseEntity<String>> queue(@RequestBody final String value) {
        log.debug("Adding value to queue");

        final Message<String> message = new GenericMessage<>(value);

        return new Callable<ResponseEntity<String>>() {
            @Override
            public ResponseEntity<String> call() {
                log.debug("Handling request / 1");

                final boolean sent = queueChannel.send(message);

                return new ResponseEntity<>(message.getPayload(), sent ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
            }
        };
    }

    @RequestMapping(value = "/queue/{value}", method = RequestMethod.GET)
    public Callable<ResponseEntity<String>> queueValue(@PathVariable final String value) {
        log.debug("Adding value to queue");

        final Message<String> message = new GenericMessage<>(value);

        return new Callable<ResponseEntity<String>>() {
            @Override
            public ResponseEntity<String> call() {
                log.debug("Handling request / 1");

                final boolean sent = queueChannel.send(message);

                return new ResponseEntity<>(message.getPayload(), sent ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
            }
        };
    }

}
