package azmain.github.io.domain.auth;

import javax.validation.constraints.NotNull;

public class AuthRequest {

    @NotNull
    private String userNameOrEmail;

    @NotNull
    private String password;

    public AuthRequest() {
    }

    public String getUserNameOrEmail() {
        return userNameOrEmail;
    }

    public void setUserNameOrEmail(String userNameOrEmail) {
        this.userNameOrEmail = userNameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
