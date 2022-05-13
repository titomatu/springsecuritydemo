package io.tamatu.springsecurityjwt.repository;

import io.tamatu.springsecurityjwt.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByRolename(String name);
}
