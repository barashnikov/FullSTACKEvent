package be.bt.businesstraining.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long idEvent;
    private String name;
    private String description;
    private int nbPlaces;
    private int nbParticipants;
    private double price;
    @JsonIgnore
    @ManyToOne
    private User idUser;
    private String dateDeb;
    private String dateFin;
    private String localisation;

    public Event() {
    }

    public Event(String name, String description, int nbPlaces, int nbParticipants, double price,User idUser, String dateDeb, String dateFin, String localisation){
        this.name = name;
        this.description =  description;
        this.nbPlaces = nbPlaces;
        this.nbParticipants = nbParticipants;
        this.price = price;
        this.idUser = idUser;
        this.dateDeb = dateDeb;
        this.dateFin =  dateFin;
        this.localisation = localisation;
    }
    public Event(String name, String description, int nbPlaces, int nbParticipants, double price, String dateDeb, String dateFin, String localisation){
        this.name = name;
        this.description =  description;
        this.nbPlaces = nbPlaces;
        this.nbParticipants = nbParticipants;
        this.price = price;
        this.dateDeb = dateDeb;
        this.dateFin =  dateFin;
        this.localisation = localisation;
    }


    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}
