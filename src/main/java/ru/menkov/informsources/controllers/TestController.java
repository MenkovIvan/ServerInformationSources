package ru.menkov.informsources.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/hello")
    public String helloWorld(){
        return "hello kusok pidarasiny";
    }

    @RequestMapping("/math")
    public String math(){
        int a = 5;
        int b = 7;
        int c = a+b;
        return "math =" + c;
    }

    @RequestMapping(value = "/test",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public String test(){
        return "error";
    }

    @RequestMapping("/kristina")
    public String KristinaClick(){
        return "lalalalalalalalala love kristina";
    }
}
