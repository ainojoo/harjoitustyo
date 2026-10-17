package fi.ainojoro.harjoitustyo.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.ainojoro.harjoitustyo.domain.User;
import fi.ainojoro.harjoitustyo.domain.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable("id") Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @PostMapping
    public User addNewUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User editUser(@PathVariable("id") Long userId, @Valid @RequestBody User updatedUser) {
        User user = userRepository.findById(userId).orElseThrow();

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        user.setBday(updatedUser.getBday());
        user.setRole(updatedUser.getRole());
        
        return userRepository.save(user);
    }

}
