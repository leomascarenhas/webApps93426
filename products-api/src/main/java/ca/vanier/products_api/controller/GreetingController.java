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

        // If the locale is not informed by the client, use the default locale
        if (locale == null) {
            locale = Locale.getDefault();
        }

        // If the locale is informed by the client, but the region is not, use the default region
        if (!locale.getLanguage().isEmpty() && locale.getCountry().isEmpty()) {
            if (locale.getLanguage().equals("en")) {
                locale = new Locale("en", "CA");
            } else if (locale.getLanguage().equals("fr")) {
                locale = new Locale("fr", "CA");
            }
        }
        
        // SessionLocaleResolver
        // Doc: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-servlet/localeresolver.html#mvc-localeresolver-session
        // SessionLocaleResolver resolver = new SessionLocaleResolver();
        // resolver.setDefaultLocale(locale);

        // Locale context based on the user's request
        // Create Currency Formatter using the given local (received by the client)
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        // Format the balance using the Currency Formatter
        String balance = currencyFormat.format(12345.67);
        System.out.println("Balance: " + balance);
    
        // Compile the message using the message source
        String greetingMessage = messageSource.getMessage(
            // Message key
            "customerBalance", 
            // Arguments to be replaced in the message
            new Object[]{name, balance},
            // Locale containing the language and region informed by the client
            locale);
        
        return ResponseEntity.ok(greetingMessage);
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> getMethodName(
        @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(messageSource.getMessage("greeting", null, locale));
    }
    
}
