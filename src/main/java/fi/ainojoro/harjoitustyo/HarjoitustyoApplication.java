package fi.ainojoro.harjoitustyo;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fi.ainojoro.harjoitustyo.domain.Event;
import fi.ainojoro.harjoitustyo.domain.EventRepository;
import fi.ainojoro.harjoitustyo.domain.Role;
import fi.ainojoro.harjoitustyo.domain.RoleRepository;
import fi.ainojoro.harjoitustyo.domain.User;
import fi.ainojoro.harjoitustyo.domain.UserRepository;

@SpringBootApplication
public class HarjoitustyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	/* @Bean
	CommandLineRunner harkkademo(EventRepository eventRepository, UserRepository userRepository,
			RoleRepository roleRepository) {
		return (args) -> {
			eventRepository.save(new Event("Lajitreeni", "ilman kamoja", LocalDate.of(2026,11,13)));
			eventRepository.save(new Event("Kotipeli vs Turku", "KO klo12, paikalla viimeistään klo 10", LocalDate.of(2026,12,3)));
			Role coach = new Role("valmentaja");
			Role player = new Role("pelaaja");
			roleRepository.save(coach);
			roleRepository.save(player);
			userRepository.save(new User("maijamaija", "$2a$10$DwpFC1HhCvUbO42sQK4KYer0wcPMeQg1lnyXP4cWYfs72juyaT9lC",
					"Maija Meikäläinen", player));
			userRepository.save(new User("pekkkapekka", "$2a$10$6NRa/7yRmRYGDJPTEor/zuB5TizNxcX3veDDlFwluzD6fh9o37FbS",
					"Pekka Puupää", coach));
			userRepository.save(new User("admin", "$2a$10$Gh.o3zEwhKTPk/ZKvrz47.EZaLwht..NplKGeTpFoX/y.hqvQKp0e", "Admin", coach));
			userRepository.save(
					new User("user", "$2a$10$Ifer7bT/EC3s6DS9nIEl3u4pNs1LK.zP6aNnVdL36wWFc0xI0Z/hq", "User", player));

		};
	}*/

}
