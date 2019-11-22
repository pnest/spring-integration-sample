package com.github.pnest.sample.springintegration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api")
class ApiController {

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    ResponseEntity<Void> getStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
