package be.bt.businesstraining.service;

import be.bt.businesstraining.domain.Event;
import be.bt.businesstraining.domain.User;
import be.bt.businesstraining.repository.EventRepository;

import be.bt.businesstraining.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@Service
public class EventServiceImpl implements EventService {

    // =================================
    // ========= Attributes ==========
    // =================================

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);
    private UserRepository usersRepository;
    private EventRepository eventRepository;


    // =================================
    // ========= Constructors ==========
    // =================================

    public EventServiceImpl(UserRepository usersRepository, EventRepository eventRepository ) {
        this.usersRepository = usersRepository;
        this.eventRepository = eventRepository;

    }

    @Override
    public User determinateUser(Principal user) {
        // Déterminer l'utilisateur à partir du principal
        String authenticatedUser = user.getName();

        // Recharger l'état persistant actuel de cet utilisateur (comprenant l'ancien montant)
        return usersRepository.findByName(authenticatedUser);

    }

    @Override
    public List<Event> findAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public ResponseEntity<?> newEvent(Principal user, Event event) {
        eventRepository.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    @Override
    public void updateEvent(Principal user, Event event) {
        eventRepository.save(event);

    }

    @Override
    public void deleteEvent(Principal user, String EventName) {
        Event event = eventRepository.findByName(EventName);

        if (eventRepository.findByName(EventName) != null) {
            eventRepository.delete(event);
        }


    }
    @Override
    public ResponseEntity<?> joinEvent(User user, String eventName,int nbPlaces,int nbParticipants){
        Event eventReceipt = eventRepository.findByName(eventName);
        
        try{
            if(eventReceipt != null && nbPlaces < nbParticipants ){
                eventReceipt.setIdUser(user);
                eventReceipt.setNbPlaces(+1);
                eventRepository.save(eventReceipt);
                return new ResponseEntity<>(eventReceipt,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(eventReceipt,HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception ex){
            return new ResponseEntity<String>("Erreur lors de l'enregistrement :" +ex.getMessage(),HttpStatus.CONFLICT);
        }

    }




}
