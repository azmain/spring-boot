package azmain.github.io.service.auth;

import azmain.github.io.domain.UserRegistration;
import azmain.github.io.domain.auth.AuthRequest;
import azmain.github.io.domain.auth.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest authRequest);

    String register(UserRegistration userRegistration);
}
