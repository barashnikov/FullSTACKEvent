package be.bt.businesstraining.rest;

import be.bt.businesstraining.domain.Event;
import be.bt.businesstraining.domain.User;
import be.bt.businesstraining.repository.EventRepository;

import be.bt.businesstraining.repository.UserRepository;
import be.bt.businesstraining.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:4200")
public class EventResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventResource.class);
    private EventRepository eventRepository;
    private UserRepository userRepository;

    private EventService eventService;


    public EventResource(EventRepository eventRepository, UserRepository usersRepository,EventService eventService) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
        this.userRepository = usersRepository;

    }

    @PostMapping("/newEvent")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> newEvent(@RequestBody Event event) {
        LOGGER.info("================  Recu l évent"+ event.getName()+ " "+ event.getPrice()+ " "+ event.getNbParticipants()+ " "+ event.getNbPlaces());

        try {

            Event resultEvent = eventRepository.findByName(event.getName());
            // Tester si le nom d'utilisatur est déjà réservé
            if (resultEvent != null) {
                LOGGER.info("The event : " + event.getName() + " is already taken !");
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);

            } else {

                eventRepository.save(event);
                LOGGER.info("The event :" + event.getName() + " has been added to the database !");

                return new ResponseEntity<>(event, HttpStatus.CREATED);

            }

        } catch (Exception ex) {
            LOGGER.error("Exception lors de l'enregistrement de l'utlisateur:"+ ex);
            return new ResponseEntity<String>("Erreur lors de l'enregistrement : " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getallEvent")
    public ResponseEntity<?> getAllEvent (){
        List<Event> events = null;

        try {
            events = eventService.findAllEvent();
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.SEE_OTHER);
        }
    }
    @PostMapping("/joinEvent")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> joinEvent(@RequestBody User user, String eventName, int nbPlaces, int nbParticipants){
        return eventService.joinEvent(user,eventName,nbPlaces,nbParticipants);
    }


}
