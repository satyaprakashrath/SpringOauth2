package com.appsdevblog.ws.api.resourceserver.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/status")
    public String status(){
        return "working...";
    }

    @Secured("ROLE_developer")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id){
        return "User "+id+" is deleted";

    }
}