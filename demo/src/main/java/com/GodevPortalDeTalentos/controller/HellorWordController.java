package com.GodevPortalDeTalentos.controller;

import com.GodevPortalDeTalentos.domain.User;
import com.GodevPortalDeTalentos.service.HelloWordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PortalDeTalentos/Go-Dev")
public class HellorWordController {
    private HelloWordService helloWordService;

    public HellorWordController(HelloWordService helloWordService){
        this.helloWordService = helloWordService;
    }
    @GetMapping
    public String hellorWord(){
        return helloWordService.hellorWord("Davi meu chapa");
    }

    @PostMapping("")
    public String helloWorPost(@RequestBody User body){
        return "hello Word";
    }
}
