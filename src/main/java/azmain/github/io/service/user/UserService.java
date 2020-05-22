package azmain.github.io.service.user;

import azmain.github.io.domain.user.AppUser;

public interface UserService {

    AppUser getAppUserByUsername(String userName);
}
