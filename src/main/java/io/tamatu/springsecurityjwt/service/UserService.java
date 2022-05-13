package io.tamatu.springsecurityjwt.service;

import io.tamatu.springsecurityjwt.model.AppRole;
import io.tamatu.springsecurityjwt.model.AppUser;

import java.util.List;

public interface UserService {

    AppUser saveAppUser(AppUser user);
    AppRole saveAppRole(AppRole role);
    void addRoleToUSer(String username, String rolename);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
