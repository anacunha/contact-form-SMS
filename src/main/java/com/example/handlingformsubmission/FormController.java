package com.example.handlingformsubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    private final DynamoDbEnhanced dynamoDb;
    private final PublishTextSMS publishText;

    public FormController(final DynamoDbEnhanced dynamoDb, final PublishTextSMS publishText) {
        this.dynamoDb = dynamoDb;
        this.publishText = publishText;
    }

    @GetMapping("/")
    public String contactForm(final Model model) {
        model.addAttribute("form", new Form());
        return "form";
    }

    @PostMapping("/form")
    public String formSubmit(@ModelAttribute final Form form) {
        dynamoDb.injectDynamoItem(form);
        publishText.sendMessage(form.getId());
        return "result";
    }
}
