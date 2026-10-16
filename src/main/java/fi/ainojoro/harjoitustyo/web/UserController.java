package fi.ainojoro.harjoitustyo.web;

import fi.ainojoro.harjoitustyo.domain.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fi.ainojoro.harjoitustyo.domain.User;
import fi.ainojoro.harjoitustyo.domain.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller 
public class UserController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
    @GetMapping("/users/new")
    public String addNewUser(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "adduser";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable ("id") Long userId, Model model) {
        userRepository.deleteById(userId);
        return "redirect:../../users";
    }
    @GetMapping("/users/edit/{id}")
    public String editBook(@PathVariable ("id") Long userId, Model model){
        model.addAttribute("oldUser", userRepository.findById(userId));
        model.addAttribute("roles", roleRepository.findAll());
        return "edituser";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User newUser, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("users", newUser);
            model.addAttribute("roles", roleRepository.findAll());
            return "adduser";
        }
        
        userRepository.save(newUser);
        return "redirect:../users";
    }
    

}
