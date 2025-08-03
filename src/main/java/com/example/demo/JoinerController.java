package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

  
    @GetMapping("/delete/{id}")
    public String deleteEntry(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/admin";
    }

    
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        FormData data = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("formData", data);
        return "form";
    }

    @PostMapping("/update")
public String updateEmployee(@ModelAttribute FormData updatedData) {
    Optional<FormData> optional = repo.findById(updatedData.getId());
    if (optional.isPresent()) {
        FormData employee = optional.get();
        employee.setName(updatedData.getName());
        employee.setEmail(updatedData.getEmail());
        employee.setUsername(updatedData.getUsername());
        employee.setLocation(updatedData.getLocation());
        repo.save(employee);
    }
    return "redirect:/admin";
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    repo.deleteById(id);
    return ResponseEntity.ok().build();
}

}