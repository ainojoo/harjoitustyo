package fi.ainojoro.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import fi.ainojoro.harjoitustyo.domain.Event;
import fi.ainojoro.harjoitustyo.domain.EventParticipant;
import fi.ainojoro.harjoitustyo.domain.EventParticipantRepository;
import fi.ainojoro.harjoitustyo.domain.EventRepository;
import fi.ainojoro.harjoitustyo.domain.ParticipationStatus;
import fi.ainojoro.harjoitustyo.domain.User;
import fi.ainojoro.harjoitustyo.domain.UserRepository;

@DataJpaTest 
@ActiveProfiles("test")
public class EventParticipantRepositoryTest {

    @Autowired
    private EventParticipantRepository epRepository;

    @Autowired 
    private EventRepository eventRepository;

    @Autowired 
    private UserRepository userRepository;

    @Test
    public void findByParticipantAndEventFindsParticipationStatusOut(){
        User user = userRepository.findByUsername("pekkap");
        Event event = eventRepository.findById(1L).orElseThrow();

        EventParticipant participation = epRepository.findByParticipantAndEvent(user, event);

        assertThat(participation).isNotNull();
        assertThat(participation.getStatus()).isEqualTo(ParticipationStatus.OUT);

    }

    @Test
    public void findByParticipantAndEventFindsParticipationStatusIn(){
        User user = userRepository.findByUsername("pekkap");
        Event event = eventRepository.findById(2L).orElseThrow();

        EventParticipant participation = epRepository.findByParticipantAndEvent(user, event);

        assertThat(participation).isNotNull();
        assertThat(participation.getStatus()).isEqualTo(ParticipationStatus.IN);

    }

    @Test
    public void findByParticipantAndEventWhenNoParticipation(){
        User user = userRepository.findByUsername("maijam");
        Event event = eventRepository.findById(2L).orElseThrow();

        EventParticipant participation = epRepository.findByParticipantAndEvent(user, event);

        assertThat(participation).isNull();
    }

}
