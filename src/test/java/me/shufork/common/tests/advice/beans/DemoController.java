package me.shufork.common.tests.advice.beans;

import me.shufork.common.exceptions.RecordNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(path = {"/hello"},method = RequestMethod.GET)
    public Object getObject(){
        return "hello";
    }

    @RequestMapping(path = {"/get-null"},method = RequestMethod.GET)
    public Object getNull(){
        return null;
    }

    @RequestMapping(path = {"/will-throw"},method = RequestMethod.GET)
    public Object throwHello(@RequestParam String name){
        throw new RecordNotFoundException("name",name);
    }
}
