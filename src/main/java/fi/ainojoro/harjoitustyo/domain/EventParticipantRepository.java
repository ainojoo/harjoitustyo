package fi.ainojoro.harjoitustyo.domain;

import org.springframework.data.repository.CrudRepository;

public interface EventParticipantRepository extends CrudRepository<EventParticipant, Long> {
    EventParticipant findByParticipantAndEvent(User user, Event event);
}
