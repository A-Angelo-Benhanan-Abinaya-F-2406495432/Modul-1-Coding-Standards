package id.ac.ui.cs.advprog.eshop.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }
}
