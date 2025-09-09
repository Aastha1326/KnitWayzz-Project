package com.example.KnitWayzz.Service;


import com.example.KnitWayzz.Entity.LoginEntity;
import com.example.KnitWayzz.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public LoginEntity save(LoginEntity login){
        return loginRepository.save(login);
    }
}
