package azmain.github.io.service.impl;

import azmain.github.io.domain.UserRegistration;
import azmain.github.io.domain.auth.AuthRequest;
import azmain.github.io.domain.auth.AuthResponse;
import azmain.github.io.repository.jpa.RoleRepository;
import azmain.github.io.repository.jpa.UserRepository;
import azmain.github.io.repository.schema.Role;
import azmain.github.io.repository.schema.User;
import azmain.github.io.security.CustomUserDetailsService;
import azmain.github.io.security.JwtUtility;
import azmain.github.io.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthRequest authRequest) {

        try{
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUserNameOrEmail(),
                            authRequest.getPassword()
                    ));
        }
        catch (BadCredentialsException e){
            logger.error(e.getMessage());
            throw new RuntimeException("Incorrect username or password.");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUserNameOrEmail());

        String jwt = jwtUtility.generateToken(userDetails);

        return new AuthResponse(jwt);

    }

    @Override
    @Transactional
    public String register(UserRegistration userRegistration) {
        if(userRepository.existsByUserName(userRegistration.getUserName())){
            throw new RuntimeException("Username already exists.");
        }
        if(userRepository.existsByEmail(userRegistration.getEmail())){
            throw new RuntimeException("Email already exists.");
        }
        if(!userRegistration.getPassword().equals(userRegistration.getConfirmPassword())){
           throw new RuntimeException("Passwords don't match.");
        }

        Role role = roleRepository.findByRoleName("USER").orElse(null);
        if(role == null){
            role = roleRepository.save(new Role().setRoleName("USER"));
        }

        User user = new User()
                .setUserName(userRegistration.getUserName())
                .setName(userRegistration.getName())
                .setEmail(userRegistration.getEmail())
                .setPassword(passwordEncoder.encode(userRegistration.getPassword()))
                .setRoles(Arrays.asList(role));

        userRepository.save(user);
        return "User created successfully.";
    }
}
