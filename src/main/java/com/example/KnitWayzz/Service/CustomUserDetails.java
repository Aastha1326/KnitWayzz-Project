package com.example.KnitWayzz.Service;

import com.example.KnitWayzz.Entity.LoginEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final LoginEntity loginEntity;

    public CustomUserDetails(LoginEntity loginEntity) {
        this.loginEntity = loginEntity;
    }

    @Override
    public String getUsername() {
        return loginEntity.getEmail();
    }

    @Override
    public String getPassword() {
        return loginEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    public String getUserName() {
        return getUsername();
    }
}

