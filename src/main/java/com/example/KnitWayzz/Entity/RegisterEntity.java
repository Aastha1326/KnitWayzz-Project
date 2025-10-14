package com.example.KnitWayzz.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "register")
public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Enter a valid email")
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    @Column(name = "reg_name")
    private String regName;

    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile must start with 6-9 and be 10 digits")
    @Column(name = "reg_mobile")
    private String regMobile;

    @NotBlank
    @Column(name = "reg_university")
    private String regUniversity;

    @NotBlank
    @Column(name = "reg_roll")
    private String regRoll;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(name = "reg_password")
    private String regPassword;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(name = "reg_confirmPassword")
    private String regConfirmPassword;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRegName() { return regName; }
    public void setRegName(String regName) { this.regName = regName; }

    public String getRegMobile() { return regMobile; }
    public void setRegMobile(String regMobile) { this.regMobile = regMobile; }

    public String getRegUniversity() { return regUniversity; }
    public void setRegUniversity(String regUniversity) { this.regUniversity = regUniversity; }

    public String getRegRoll() { return regRoll; }
    public void setRegRoll(String regRoll) { this.regRoll = regRoll; }

    public String getRegPassword() { return regPassword; }
    public void setRegPassword(String regPassword) { this.regPassword = regPassword; }

    public String getRegConfirmPassword() {
        return regConfirmPassword;
    }

    public void setRegConfirmPassword(String regConfirmPassword) {
        this.regConfirmPassword = regConfirmPassword;
    }

}

