package be.bt.businesstraining.repository;

import be.bt.businesstraining.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EventRepository extends JpaRepository<Event,Long> {

    Event findByName(String eventName);

}
