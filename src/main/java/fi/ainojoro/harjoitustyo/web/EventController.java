package fi.ainojoro.harjoitustyo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import fi.ainojoro.harjoitustyo.domain.Event;
import fi.ainojoro.harjoitustyo.domain.EventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller 
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "home";
    }
    
    @GetMapping("/newevent")
    public String addNewUser(Model model) {
        model.addAttribute("event", new Event());
        return "addevent";
    }

    @GetMapping("/delete-event/{id}")
    public String deleteUser(@PathVariable ("id") Long eventId, Model model) {
        eventRepository.deleteById(eventId);
        return "redirect:../home";
    }
    @GetMapping("/editevent/{id}")
    public String editBook(@PathVariable ("id") Long eventId, Model model){
        model.addAttribute("event", eventRepository.findById(eventId));
        return "editevent";
    }

    @PostMapping("/save-event")
    public String saveUser(@ModelAttribute Event event, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("event", event);

            if (event.getId() != null) {
                return "editevent";
            }

            return "addevent";
        }
        
        eventRepository.save(event);
        return "redirect:/home";
    }

}
