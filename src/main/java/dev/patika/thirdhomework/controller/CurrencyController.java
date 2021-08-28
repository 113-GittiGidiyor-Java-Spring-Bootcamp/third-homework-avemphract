package dev.patika.thirdhomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/request")
    public String currency(@RequestParam String type, @RequestParam double amount){
        if (type.equals("tl"))
            return restTemplate.getForEntity("http://localhost:8081/currency/totl?amount="+String.valueOf(amount), String.class).getBody();
        else if (type.equals("us"))
            return restTemplate.getForEntity("http://localhost:8081/currency/tous?amount="+String.valueOf(amount),String.class).getBody();
        else
            return "type only be tl or us";
    }
}
