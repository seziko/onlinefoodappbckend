package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") // bunu sonra ekleyeceksin
@RestController
@RequestMapping("/initial")
public class HelloWorldController {

    //@GetMapping(path = "/helloworld")
    @RequestMapping(method = RequestMethod.GET,path = "/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/helloworldbean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @RequestMapping(method = RequestMethod.GET,path = "/helloworldbean/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World %s",name));
        //throw new RuntimeException("Error occured!");
    }
}
