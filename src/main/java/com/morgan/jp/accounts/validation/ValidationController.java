package com.morgan.jp.accounts.validation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @GetMapping("/validate/{name}")
    public String validate(@PathVariable String name) {
        return "Hello " + name;
    }
}
