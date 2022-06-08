package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.MyUserDetails;
import com.example.demo.SuperAdminDetails;

public abstract class BaseController {
	protected MyUserDetails getLoggedUser() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
	protected SuperAdminDetails getLoggedSA() {
        return (SuperAdminDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
	
}
