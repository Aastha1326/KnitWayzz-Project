package com.example.KnitWayzz.Service;

import com.example.KnitWayzz.Entity.RegisterEntity;
import com.example.KnitWayzz.Entity.LoginEntity;
import com.example.KnitWayzz.Repository.LoginRepository;
import com.example.KnitWayzz.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder;

    public RegisterEntity save(RegisterEntity register) {
        if (register.getRegPassword() == null || !register.getRegPassword().equals(register.getRegConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }


        if (registerRepository.findByEmail(register.getEmail()) != null) {
            throw new IllegalArgumentException("Email already registered!");
        }

        String hashedPassword = passwordEncoder.encode(register.getRegPassword());
        register.setRegPassword(hashedPassword);

        register.setRegConfirmPassword(null);

        RegisterEntity saved = registerRepository.save(register);

        LoginEntity login = new LoginEntity();
        login.setEmail(register.getEmail());
        login.setPassword(hashedPassword);
        loginRepository.save(login);

        return saved;
    }
}
