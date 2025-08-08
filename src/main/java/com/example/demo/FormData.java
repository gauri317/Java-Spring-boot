package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "form_data")
public class FormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String message;
    private String username;
    private String password;
    private String location;
    private String registrationNumber; // field is here

    public FormData() {
    }

    public FormData(String name, String email, String message, String username, String password, String location) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.username = username;
        this.password = password;
        this.location = location;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegistrationNumber() { // ✅ Added getter
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) { // ✅ Added setter
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "FormData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }
}
