package com.project.lms.ExpenseTrackerLms.services.ContactForm;

import com.project.lms.ExpenseTrackerLms.entity.ContactForm;
import com.project.lms.ExpenseTrackerLms.repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactFormService {
    @Autowired
    private ContactFormRepository repository;

    public void saveContactForm(ContactForm contactForm) {
        repository.save(contactForm);
    }
}
