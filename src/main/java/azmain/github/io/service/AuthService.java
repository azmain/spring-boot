package azmain.github.io.service;

import azmain.github.io.domain.auth.AuthRequest;
import azmain.github.io.domain.auth.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest authRequest);
}
