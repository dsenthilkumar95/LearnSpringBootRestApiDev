package com.improve.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.improve.service.ConsumeSlow;
import com.improve.service.SlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/api")
public class SlowController {

    @Autowired
    SlowService slowService;
    @Autowired
    ConsumeSlow consumeSlow;

    @GetMapping(path = "/slow/{secs}", produces = "application/json", consumes = "application/json")
    public String slowProcess(@PathVariable int secs){
        String output = slowService.slowProcess(secs);
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("output",output);
        return node.toString();
    }

    @GetMapping(path = "/consumeslows", produces = "application/json", consumes = "application/json")
    public String consumeSlowProcessParallely(){
        String outputList = consumeSlow.consumeSlowProcess();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("outputList",outputList);
        return node.toString();
    }
}
