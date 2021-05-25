package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController( UserService userService,RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("userAuth", userService.getByEmail(username));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roleSet", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "/admin";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/user-edit")
    public String updateUser(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.delete(userService.getById(id));
        return "redirect:/admin";
    }
}
