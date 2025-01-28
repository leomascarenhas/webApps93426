package ca.vanier.products_api.controller;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/balance")
    public ResponseEntity<String> getAccountTotal(
        @RequestHeader(name = "Accept-Language", required = false) Locale locale,
        @RequestParam String name) {

        // https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-servlet/localeresolver.html#mvc-localeresolver-session
        // SessionLocaleResolver resolver = new SessionLocaleResolver();
        // resolver.setDefaultLocale(locale);

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        String balance = currencyFormat.format(12345.67);
        System.out.println("Balance: " + balance);
    
        String greetingMessage = messageSource.getMessage("customerBalance", new Object[]{name, balance}, locale);
        return ResponseEntity.ok(greetingMessage);
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> getMethodName(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(messageSource.getMessage("greeting", null, locale));
    }
    
}
