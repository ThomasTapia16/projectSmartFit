package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.MyUserDetails;

public abstract class BaseController {
	protected MyUserDetails getLoggedUser() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
