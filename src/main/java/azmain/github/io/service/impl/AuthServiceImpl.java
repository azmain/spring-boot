package azmain.github.io.service.impl;

import azmain.github.io.domain.auth.AuthRequest;
import azmain.github.io.domain.auth.AuthResponse;
import azmain.github.io.security.CustomUserDetailsService;
import azmain.github.io.security.JwtUtility;
import azmain.github.io.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtility jwtUtility;

    @Override
    public AuthResponse login(AuthRequest authRequest) {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUserNameOrEmail(),
                            authRequest.getPassword()
                    ));
        }
        catch (BadCredentialsException e){
            logger.error(e.getMessage());
            throw new RuntimeException("Incorrect username or password.", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUserNameOrEmail());

        String jwt = jwtUtility.generateToken(userDetails);

        return new AuthResponse(jwt);

    }
}
