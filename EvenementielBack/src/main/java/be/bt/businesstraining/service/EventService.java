package be.bt.businesstraining.service;


import be.bt.businesstraining.domain.Event;
import be.bt.businesstraining.domain.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface EventService {

    ResponseEntity<?> newEvent (Principal user, Event event);
    void updateEvent (Principal user,Event event);
    void deleteEvent(Principal user, String EventName);
    User determinateUser (Principal user);
    List<Event> findAllEvent();
    ResponseEntity<?> joinEvent (User user,String eventName,int nbPlaces,int nbParticipants);

}
