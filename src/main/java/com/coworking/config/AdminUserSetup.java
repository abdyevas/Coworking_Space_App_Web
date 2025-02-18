package com.coworking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.coworking.model.Users;
import com.coworking.repository.UserRepository;

@Component
public class AdminUserSetup {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    public AdminUserSetup(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        try {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users adminUser = new Users();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("11111")); 
                adminUser.setRole("ADMIN");
                userRepository.save(adminUser); 
                System.out.println("Admin user created successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error during admin user creation: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
