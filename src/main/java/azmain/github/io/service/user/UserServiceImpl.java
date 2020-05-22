package azmain.github.io.service.user;

import azmain.github.io.domain.user.AppUser;
import azmain.github.io.exception.ResourceNotFoundException;
import azmain.github.io.repository.jpa.UserRepository;
import azmain.github.io.repository.mappers.UserMapper;
import azmain.github.io.repository.schema.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired private UserMapper userMapper;

    @Override
    public AppUser getAppUserByUsername(String userName) {
        UserEntity userEntity = userRepository
                .findByUserName(userName)
                .orElseThrow(()->new ResourceNotFoundException("User not found with this username."));

        return userMapper.entityToDomain().map(userEntity);
    }
}
