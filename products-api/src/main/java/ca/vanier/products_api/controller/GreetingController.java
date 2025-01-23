package ca.vanier.products_api.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/greeting")
    public ResponseEntity<String> getMethodName(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(messageSource.getMessage("greeting", null, locale));
    }
    
}
