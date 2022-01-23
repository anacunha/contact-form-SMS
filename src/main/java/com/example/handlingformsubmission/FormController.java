package com.example.handlingformsubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @GetMapping("/")
    public String contactForm(final Model model) {
        model.addAttribute("form", new Form());
        return "form";
    }

    @PostMapping("/form")
    public String formSubmit(@ModelAttribute final Form form) {

        return "result";
    }
}