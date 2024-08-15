package com.example.RestApi.inDetail.Internationalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource msg;

    @GetMapping(path = "/hello-world")
    public String getGreetings(){
        Locale locale = LocaleContextHolder.getLocale();
        return msg.getMessage("good.morning.message",
                null,"this default message",
                locale);
    }
}
