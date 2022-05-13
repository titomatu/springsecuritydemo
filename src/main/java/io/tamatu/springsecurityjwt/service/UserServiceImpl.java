package io.tamatu.springsecurityjwt.service;

import io.tamatu.springsecurityjwt.model.AppRole;
import io.tamatu.springsecurityjwt.model.AppUser;
import io.tamatu.springsecurityjwt.repository.AppRoleRepository;
import io.tamatu.springsecurityjwt.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveAppUser(AppUser user) {
        log.info("Saving User {} into the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);

        if(user == null){
            log.error("User Not Found");
            throw  new UsernameNotFoundException("User Not Found");
        }

        log.info("User {} found in the database", username);

        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities
                =new ArrayList<>();

        user.getRoles().forEach(
                role -> {
                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
                }
        );

        return new User(username, user.getPassword(), simpleGrantedAuthorities);
    }
}
