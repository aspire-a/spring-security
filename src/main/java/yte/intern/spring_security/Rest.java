package yte.intern.spring_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {
    @GetMapping("/rest")
    public String rest() {
        return "rest.html";
    }

    @GetMapping("/user")
    public String user() {
        return "sen bi user'sın";
    }

    @GetMapping("/admin")
    public String admin() {
        return "sen bi admin'sin";
    }
}
