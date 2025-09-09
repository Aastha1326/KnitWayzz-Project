package com.example.KnitWayzz.Repository;

import com.example.KnitWayzz.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity,Long>{
    LoginEntity findByEmail(String email);
}