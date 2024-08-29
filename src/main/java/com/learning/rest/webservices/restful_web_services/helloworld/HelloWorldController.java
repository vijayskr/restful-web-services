package com.learning.rest.webservices.restful_web_services.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //    @RequestMapping("/hello-world")
    @GetMapping(path="/hello-world")
    public String helloworld() {
        return "Hello World";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloworldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path="/hello-world-bean-inter")
    public String helloworldBeanInter() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
//        return new HelloWorldBean("Hello World Internationalized");
    }
}
