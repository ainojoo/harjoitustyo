package fi.ainojoro.harjoitustyo.web;

import java.time.LocalDateTime;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fi.ainojoro.harjoitustyo.domain.Event;
import fi.ainojoro.harjoitustyo.domain.EventParticipant;
import fi.ainojoro.harjoitustyo.domain.EventParticipantRepository;
import fi.ainojoro.harjoitustyo.domain.EventRepository;
import fi.ainojoro.harjoitustyo.domain.ParticipationStatus;
import fi.ainojoro.harjoitustyo.domain.User;
import fi.ainojoro.harjoitustyo.domain.UserRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventParticipantRepository participantRepository;

    public EventController(EventRepository eventRepository, UserRepository userRepository,
            EventParticipantRepository participantRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
    }

    @GetMapping("/home")
    public String allEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "home";
    }

    @PreAuthorize("hasAuthority('valmentaja')")
    @GetMapping("/newevent")
    public String addNewEvent(Model model) {
        model.addAttribute("event", new Event());
        return "addevent";
    }

    @PreAuthorize("hasAuthority('valmentaja')")
    @GetMapping("/delete-event/{id}")
    public String deleteEvent(@PathVariable("id") Long eventId, Model model) {
        eventRepository.deleteById(eventId);
        return "redirect:../home";
    }

    @PreAuthorize("hasAuthority('valmentaja')")
    @GetMapping("/editevent/{id}")
    public String editEvent(@PathVariable("id") Long eventId, Model model) {
        model.addAttribute("event", eventRepository.findById(eventId).orElseThrow());
        return "editevent";
    }

    @PreAuthorize("hasAuthority('valmentaja')")
    @PostMapping("/save-event")
    public String saveEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", event);

            if (event.getId() != null) {
                return "editevent";
            }

            return "addevent";
        }

        eventRepository.save(event);
        return "redirect:/home";
    }

    @GetMapping("/event/{id}")
    public String showEvent(@PathVariable("id") Long eventId, Model model) {
        model.addAttribute("event", eventRepository.findById(eventId).orElseThrow());
        return "event";
    }

    @PostMapping("/event/{id}/in")
    public String registerIn(@PathVariable("id") Long eventId, Authentication authentication) {

        User user = userRepository.findByUsername(authentication.getName());
        Event event = eventRepository.findById(eventId).orElseThrow();
        EventParticipant participant = participantRepository.findByParticipantAndEvent(user, event);

        if (participant != null) {
            if (participant.getStatus() == ParticipationStatus.OUT) {
                participant.setStatus(ParticipationStatus.IN);
                participant.setUpdatedAt(LocalDateTime.now());
                participantRepository.save(participant);
            }
        } else {
            participant = new EventParticipant();
            participant.setEvent(event);
            participant.setParticipant(user);
            participant.setStatus(ParticipationStatus.IN);
            participant.setUpdatedAt(LocalDateTime.now());

            participantRepository.save(participant);
        }

        return "redirect:/home";
    }

    @PostMapping("/event/{id}/out")
    public String registerOut(@PathVariable("id") Long eventId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        Event event = eventRepository.findById(eventId).orElseThrow();
        EventParticipant participant = participantRepository.findByParticipantAndEvent(user, event);

        if (participant != null) {
            if (participant.getStatus() == ParticipationStatus.IN) {
                participant.setStatus(ParticipationStatus.OUT);
                participant.setUpdatedAt(LocalDateTime.now());
                participantRepository.save(participant);
            }
        } else {
            participant = new EventParticipant();
            participant.setEvent(event);
            participant.setParticipant(user);
            participant.setStatus(ParticipationStatus.OUT);
            participant.setUpdatedAt(LocalDateTime.now());

            participantRepository.save(participant);
        }

        return "redirect:/home";
    }

}
