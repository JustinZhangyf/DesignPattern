package tech.insight.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MyController {


    @PostMapping("/api")
    public Map<String, Object> api(@RequestBody Map<String, Object> json) {
        return json;
    }
}
