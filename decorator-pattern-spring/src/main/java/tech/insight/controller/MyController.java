package tech.insight.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.insight.TimestampRequestBody;

import java.util.Map;

@RestController
public class MyController {


    @PostMapping("/api")
    public Map<String, Object> api(@TimestampRequestBody Map<String, Object> json) {
        return json;
    }
}
