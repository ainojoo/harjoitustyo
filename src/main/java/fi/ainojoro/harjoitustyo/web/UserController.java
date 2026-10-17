package fi.ainojoro.harjoitustyo.web;

import fi.ainojoro.harjoitustyo.domain.RoleRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fi.ainojoro.harjoitustyo.domain.User;
import fi.ainojoro.harjoitustyo.domain.UserRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @PreAuthorize("hasAuthority('valmentaja')")
    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "adduser";
    }

    @PreAuthorize("hasAuthority('valmentaja')")
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId, Model model) {
        userRepository.deleteById(userId);
        return "redirect:../../users";
    }

    // nyt ei voi muokata omiakaan tietoja, katotaan jos fiksaan tän myöhemmin
    @PreAuthorize("hasAuthority('valmentaja')")
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("oldUser", userRepository.findById(userId));
        model.addAttribute("roles", roleRepository.findAll());
        return "edituser";
    }

    @PreAuthorize("hasAuthority('valmentaja')")
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", newUser);
            model.addAttribute("roles", roleRepository.findAll());
            return "adduser";
        }

        userRepository.save(newUser);
        return "redirect:../users";
    }

    @PreAuthorize("hasAuthority('valmentaja')")
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("oldUser") User oldUser, BindingResult bindingResult, Model model) {
          System.out.println("UPDATEA KUTSUTTU");
          System.out.println(bindingResult.getAllErrors());
        if (bindingResult.hasErrors()) {
            model.addAttribute("oldUser", oldUser);
            model.addAttribute("roles", roleRepository.findAll());
            return "edituser";
        }

        userRepository.save(oldUser);
        return "redirect:../users";
    }

}
