package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class JoinerController {

    private final FormDataRepository repo;

    @Autowired
    public JoinerController(FormDataRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String showWelcome() {
        return "welcome";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("formData", new FormData());
        return "form";
    }

    @PostMapping("/submitForm")
    @ResponseBody
    public String handleSubmit(@RequestBody FormData data) {
        repo.save(data);
        return "{\"message\": \"Form submitted successfully!\"}";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("employees", repo.findAll());
        return "admin";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
    repo.deleteById(id);
    return ResponseEntity.ok("Employee deleted successfully");
}


    // NEW: Update Registration Number only
    @PutMapping("/updateRegistration/{id}")
    @ResponseBody
    public ResponseEntity<String> updateRegistration(@PathVariable Long id,
                                                     @RequestBody Map<String, String> payload) {
        Optional<FormData> optional = repo.findById(id);
        if (optional.isPresent()) {
            FormData employee = optional.get();
            employee.setRegistrationNumber(payload.get("registrationNumber")); // You must add this field to FormData
            repo.save(employee);
            return ResponseEntity.ok("Updated successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
