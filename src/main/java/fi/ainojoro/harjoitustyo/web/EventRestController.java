package fi.ainojoro.harjoitustyo.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.ainojoro.harjoitustyo.domain.Event;
import fi.ainojoro.harjoitustyo.domain.EventRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/events")
public class EventRestController {
    private final EventRepository eventRepository;

    public EventRestController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public Event getOneEvent(@PathVariable("id") Long eventId) {
        return eventRepository.findById(eventId).orElseThrow();
    }

    @PostMapping
    public Event addNewEvent(@Valid @RequestBody Event event) {
        return eventRepository.save(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Event editEvent(@PathVariable("id") Long eventId, @Valid @RequestBody Event updatedEvent) {
        Event event = eventRepository.findById(eventId).orElseThrow();

        event.setTitle(updatedEvent.getTitle());
        event.setDesc(updatedEvent.getDesc());
        event.setDate(updatedEvent.getDate());
        
        return eventRepository.save(event);
    }


}
