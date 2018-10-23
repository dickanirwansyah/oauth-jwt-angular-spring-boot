package com.app.authspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/access/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    public ResponseEntity<?> getHallo(){
        return ResponseEntity.ok("Hallo Admin !");
    }
}
