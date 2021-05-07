package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;



    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("user", userRepository.findUserByEmail(username));
        return "user";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model, Principal principal) {
        String username = principal.getName();
        ArrayList<User> users  = (ArrayList<User>) userRepository.findAll();
        int length = Integer.parseInt(users.stream().map(p -> p.getId()).max(Long::compare).get().toString());
        boolean[] hidden = new boolean[length+1];
        for(int i=1; i<=length; i++){
            hidden[i]=false;
        }
        model.addAttribute("user1", userRepository.findUserByEmail(username));
        model.addAttribute("users", users);
        model.addAttribute("roleSet", roleRepository.findAll());
        model.addAttribute("newUser", new User());
        model.addAttribute("hidden", hidden);
        model.addAttribute("user", new User());
        return "/admin/index";
    }
    @GetMapping("/admin/new")
    public String newUser(Model model){
        model.addAttribute("roleSet", roleRepository.findAll());
        model.addAttribute("newUser", new User());
        return "/admin/new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("newUser") User user){
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String show(Model model, @PathVariable("id") Long id){
        model.addAttribute("users", Arrays.asList(userRepository.findById(id).get()));
        return "/admin/index";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id, Principal principal){
        String username = principal.getName();
        int i = Integer.parseInt(id.toString());
        ArrayList<User> users  = (ArrayList<User>) userRepository.findAll();

        int length = Integer.parseInt(users.stream().map(p -> p.getId()).max(Long::compare).get().toString());
        boolean[] hidden = new boolean[length+1];
        hidden[i] = true;
        model.addAttribute("user1", userRepository.findUserByEmail(username));
        model.addAttribute("users", users);
        model.addAttribute("roleSet", roleRepository.findAll());
        model.addAttribute("newUser", new User());
        model.addAttribute("user", userRepository.findById(id).get());
        model.addAttribute("hidden", hidden);
        return "/admin/index";
    }

    @PostMapping("/admin/edit/{id}")
    public String update(@ModelAttribute("user") User user,  Model model){
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "redirect:/admin";
    }
}
