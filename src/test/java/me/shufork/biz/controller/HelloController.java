package me.shufork.biz.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.shufork.common.exceptions.RecordNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
public class HelloController {
    @Data
    @AllArgsConstructor
    public static class MyDto{
        private  String context;

    }
    @RequestMapping(path = {"/hello"},method = RequestMethod.GET)
    public String getObject(){
        return "hello";
    }

    @RequestMapping(path = {"/null"},method = RequestMethod.GET)
    public MyDto getNull(){
        return null;
    }

    @RequestMapping(path = {"/throw-name"},method = RequestMethod.GET)
    public MyDto throwHello(@RequestParam String name){
        throw new RecordNotFoundException("name",name);
    }

    @PostConstruct
    private void onInit(){
        log.info("{} init",getClass().getSimpleName());
    }
}
