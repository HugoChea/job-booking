package com.jobBooking.JobBooking.controller;

import com.jobBooking.JobBooking.enumeration.ERole;
import com.jobBooking.JobBooking.model.Applicant;
import com.jobBooking.JobBooking.model.Role;
import com.jobBooking.JobBooking.model.User;
import com.jobBooking.JobBooking.payload.request.LoginRequest;
import com.jobBooking.JobBooking.payload.request.RegisterRequest;
import com.jobBooking.JobBooking.payload.response.JwtResponse;
import com.jobBooking.JobBooking.payload.response.MessageResponse;
import com.jobBooking.JobBooking.repository.RoleRepository;
import com.jobBooking.JobBooking.repository.UserRepository;
import com.jobBooking.JobBooking.security.jwt.JwtUtils;
import com.jobBooking.JobBooking.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account

        AtomicBoolean isRecruiter = new AtomicBoolean(false);
        
        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    case "applicant":
                        isRecruiter.set(false);
                        Role applicantRole = roleRepository.findByName(ERole.ROLE_APPLICANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(applicantRole);
                    break;
                    case "recruiter":
                        isRecruiter.set(true);
                        Role recruiterRole = roleRepository.findByName(ERole.ROLE_RECRUITER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(recruiterRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        
        User user = new User();

        if (isRecruiter.get()){

        }
        else if (!isRecruiter.get()){
            user = new Applicant(registerRequest.getUsername(),
                    registerRequest.getEmail(),
                    encoder.encode(registerRequest.getPassword()),
                    null,
                    new HashSet<>());
        }
        else{
            user = new User(registerRequest.getUsername(),
                    registerRequest.getEmail(),
                    encoder.encode(registerRequest.getPassword()));
        }


        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
