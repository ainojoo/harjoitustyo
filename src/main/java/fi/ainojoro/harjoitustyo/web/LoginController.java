package fi.ainojoro.harjoitustyo.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.ainojoro.harjoitustyo.domain.User;
import fi.ainojoro.harjoitustyo.domain.UserRepository;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/change-password")
    public String openChangePassword() {
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String newPassword, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setMustChangePassword(false);

        userRepository.save(user);

        return "redirect:/home";
    }

}
