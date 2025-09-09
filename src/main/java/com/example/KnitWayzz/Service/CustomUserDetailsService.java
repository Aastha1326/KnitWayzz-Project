package com.example.KnitWayzz.Service;

import com.example.KnitWayzz.Entity.LoginEntity;
import com.example.KnitWayzz.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LoginEntity loginEntity = loginRepository.findByEmail(email);
        if (loginEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(loginEntity); // returns UserDetails implementation
    }

}
