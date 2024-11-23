package com.project.lms.ExpenseTrackerLms.controller;

import com.project.lms.ExpenseTrackerLms.entity.ContactForm;
import com.project.lms.ExpenseTrackerLms.services.ContactForm.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contact")
//@CrossOrigin(origins = "*")  // Set the specific origin here
public class ContactFormController {

    @Autowired
    private ContactFormService service;

    @PostMapping
    public ResponseEntity<String> submitContactForm(@RequestBody ContactForm contactForm) {
        service.saveContactForm(contactForm);
        return ResponseEntity.ok("Message sent successfully");
    }
}

