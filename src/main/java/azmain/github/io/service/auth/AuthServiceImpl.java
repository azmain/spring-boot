package azmain.github.io.service.auth;

import azmain.github.io.domain.UserRegistration;
import azmain.github.io.domain.auth.AuthRequest;
import azmain.github.io.domain.auth.AuthResponse;
import azmain.github.io.exception.MyCustomException;
import azmain.github.io.repository.jpa.RoleRepository;
import azmain.github.io.repository.jpa.UserRepository;
import azmain.github.io.repository.schema.Role;
import azmain.github.io.repository.schema.UserEntity;
import azmain.github.io.security.CustomUserDetailsService;
import azmain.github.io.security.JwtUtility;
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

import java.util.Collections;

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
        final UserEntity userEntity = userDetailsService.getUserByUserName(authRequest.getUserNameOrEmail());

        String jwt = jwtUtility.generateTokenFromUser(userEntity);

        return new AuthResponse(jwt);

    }

    @Override
    @Transactional
    public String register(UserRegistration userRegistration) {
        if(userRepository.existsByUserName(userRegistration.getUserName())){
            throw new MyCustomException("Username already exists.");
        }
        if(userRepository.existsByEmail(userRegistration.getEmail())){
            throw new MyCustomException("Email already exists.");
        }
        if(!userRegistration.getPassword().equals(userRegistration.getConfirmPassword())){
           throw new MyCustomException("Passwords don't match.");
        }

        Role role = roleRepository.findByRoleName("USER").orElse(null);
        if(role == null){
            role = roleRepository.save(new Role().setRoleName("USER"));
        }

        UserEntity userEntity = new UserEntity()
                .setUserName(userRegistration.getUserName())
                .setName(userRegistration.getName())
                .setEmail(userRegistration.getEmail())
                .setPassword(passwordEncoder.encode(userRegistration.getPassword()))
                .setRoles(Collections.singletonList(role));

        userRepository.save(userEntity);
        return "UserEntity created successfully.";
    }
}
