package com.example.project.Dealership.Controller;

import com.example.project.Dealership.Entity.AuthResponseDTO;
import com.example.project.Dealership.Entity.LoginDTO;
import com.example.project.Dealership.Entity.UserEntity;
import com.example.project.Dealership.Repository.UserEntityRepo;
import com.example.project.Dealership.Security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Entity;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserEntityRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;


    //@GetMapping(path="/basicAuth")
   // public

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){
        System.out.println(loginDto);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserEntity registerDto) {
        //check if email already exists


        if (userRepo.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setFirstname(registerDto.getFirstname());
        user.setLastname(registerDto.getLastname());
        user.setRole(registerDto.getRole());


        userRepo.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserEntity>> getUsers() {
        System.out.println(userRepo.findAll());
        // Logic to return the list of users or other resources
        return ResponseEntity.ok(userRepo.findAll());
    }

}
