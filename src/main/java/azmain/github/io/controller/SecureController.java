package azmain.github.io.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "secure")
public class SecureController {

    @GetMapping
    public ResponseEntity<?> sec(){
        return ResponseEntity.ok("Security Break.");
    }
}
