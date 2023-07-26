package com.appsdevblog.ws.api.resourceserver.controller;

import com.appsdevblog.ws.api.resourceserver.response.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/status")
    public String status(){
        return "working...";
    }

    //@Secured("ROLE_developer")
    @PreAuthorize("hasRole('developer') or #id == #jwt.subject")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "User "+id+ "and Jwt subject is "+jwt.getSubject()+" is deleted";

    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new User("Test", "Oauth", "738a4734-8fd6-4df4-9916-29211e007416");

    }
}