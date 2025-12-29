package com.GodevPortalDeTalentos.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWordService {
    public String hellorWord(String name){
        return "Consegui Lançar repositório na Web Pelo menos!"+name;
    }
}
