package fi.ainojoro.harjoitustyo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import fi.ainojoro.harjoitustyo.domain.Event;
import fi.ainojoro.harjoitustyo.domain.EventRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class EventRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void getAllEvents() throws Exception {
        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk());
    }

    @Test
    public void postNewEvent() throws Exception {
        String eventJson = """
                {
                    "title": "Testitreeni",
                    "desc": "testi",
                    "date": "2029-11-19"
                }
                """;

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eventJson))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteEvent() throws Exception {

        mockMvc.perform(delete("/api/events/3"))
                .andExpect(status().isOk());

        assertThat(eventRepository.findById(3L))
                .isEmpty();
    }

    @Test
    public void putEvent() throws Exception {
        String event = """
                {
                    "title":"Päivitetty",
                    "desc":"Tätäkin päivitetty",
                    "date":"2026-11-19"
                }
                """;

        mockMvc.perform(put("/api/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(event))
                .andExpect(status().isOk());

        Event updated = eventRepository.findById(1L).orElseThrow();

        assertThat(updated.getTitle()).isEqualTo("Päivitetty");
        assertThat(updated.getDesc()).isEqualTo("Tätäkin päivitetty");

    }

}
