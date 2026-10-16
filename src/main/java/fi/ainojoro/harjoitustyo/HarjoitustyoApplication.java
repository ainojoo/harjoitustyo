package fi.ainojoro.harjoitustyo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

	@Bean CommandLineRunner harkkademo(EventRepository eventRepository, UserRepository userRepository, RoleRepository roleRepository){
		return (args) ->{
		eventRepository.save(new Event("Lajitreeni", "ilman kamoja", "16.10.2026"));
		eventRepository.save(new Event("Kotipeli vs Turku", "KO klo12, paikalla viimeistään klo 10", "31.10.2026"));
		Role coach = new Role("valmentaja");
		Role player = new Role("pelaaja");
		roleRepository.save(coach);
		roleRepository.save(player);
		userRepository.save(new User("Maija", "maijamaija", "0101010", "1.1.1999", player));
		userRepository.save(new User("pekka", "pekkkapekka", "020202", "1.1.1999", coach));
		};
	}

}
