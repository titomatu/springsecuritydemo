package io.tamatu.springsecurityjwt.controller;

import io.tamatu.springsecurityjwt.model.AppRole;
import io.tamatu.springsecurityjwt.model.AppUser;
import io.tamatu.springsecurityjwt.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService service;

    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveAppUser(@RequestBody AppUser user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(service.saveAppUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<AppRole> saveAppRole(@RequestBody AppRole role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(service.saveAppRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUSer(@RequestBody RoleToUSerForm roletouserform){
        service.addRoleToUSer(roletouserform.getUsername(), roletouserform.getRolename());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<AppUser> getUser(String username){
        return ResponseEntity.ok(service.getUser(username));
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }

}
@Data
class RoleToUSerForm{
    private String username;
    private String rolename;
}
