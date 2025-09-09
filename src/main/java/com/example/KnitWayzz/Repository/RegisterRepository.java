package com.example.KnitWayzz.Repository;

import com.example.KnitWayzz.Entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<RegisterEntity,Long> {
    RegisterEntity findByEmail(String email);
}
