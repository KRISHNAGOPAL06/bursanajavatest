package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if admin already exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User("admin", "1234");
            userRepository.save(admin);
            System.out.println("✅ Admin user created");
        } else {
            System.out.println("ℹ️ Admin user already exists");
        }
    }
}
