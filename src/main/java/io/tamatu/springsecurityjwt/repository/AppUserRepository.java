package io.tamatu.springsecurityjwt.repository;

import io.tamatu.springsecurityjwt.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String name);
}
