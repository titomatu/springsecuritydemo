package io.tamatu.springsecurityjwt.service;

import io.tamatu.springsecurityjwt.model.AppRole;
import io.tamatu.springsecurityjwt.model.AppUser;
import io.tamatu.springsecurityjwt.repository.AppRoleRepository;
import io.tamatu.springsecurityjwt.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;

    @Override
    public AppUser saveAppUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppRole saveAppRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUSer(String username, String rolename) {
        log.info("Adding role {] to user {}", rolename, username);
        userRepository.findByUsername(username)
                .getRoles().add(roleRepository.findByRolename(rolename));
    }

    @Override
    public AppUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }
}
