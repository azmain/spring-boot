package azmain.github.io.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
public class TestController {

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Test Completed");
    }

    @GetMapping(path = "home")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("Stay at Home");
    }
}
