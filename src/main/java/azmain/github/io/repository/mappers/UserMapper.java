package azmain.github.io.repository.mappers;

import azmain.github.io.domain.movie.Movie;
import azmain.github.io.domain.role.AppUserRole;
import azmain.github.io.domain.user.AppUser;
import azmain.github.io.repository.schema.MovieEntity;
import azmain.github.io.repository.schema.UserEntity;
import azmain.github.io.util.ResultMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public ResultMapper<UserEntity, AppUser> entityToDomain(){
        return entity -> new AppUser()
                .setUsername(entity.getUserName())
                .setEmail(entity.getEmail())
                .setRoles(
                        entity.getRoles()
                            .stream()
                            .map(r-> new AppUserRole().setRoleName(r.getRoleName()))
                            .collect(Collectors.toList())
                );
    }
}
