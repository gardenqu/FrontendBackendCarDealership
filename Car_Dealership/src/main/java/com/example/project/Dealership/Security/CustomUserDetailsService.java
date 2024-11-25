package com.example.project.Dealership.Security;

import com.example.project.Dealership.Entity.UserEntity;
import com.example.project.Dealership.Repository.UserEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private final UserEntityRepo userRepo;
    @Autowired
    public CustomUserDetailsService(UserEntityRepo userRepository) {
        this.userRepo = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find the user by email (interpreted as "username")
        UserEntity user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convert the user's role to a collection of GrantedAuthority
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(mapRoleToAuthority(user.getRole()));

        return new User(
                user.getEmail(),
                user.getPassword(),
                authorities // Pass the collection here
        );
    }

    private GrantedAuthority mapRoleToAuthority(String role) {
        return new SimpleGrantedAuthority(role);
    }

}
