package com.example.jwtTutorial.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test(){
        Map map = new HashMap();
        map.put("key","value");
        return ResponseEntity.ok(map);
    }
}
