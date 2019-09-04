package be.bt.businesstraining.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;

//@Data
@Entity()
@Table(name = "user")
//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;
    private String name;
    private String surname;
    private String dateBirth;
    private String nickname;
    private String password;
    private String mail;

    public User(String name, String surname, String dateBirth, String nickname, String password, String mail, Set<Event> idEvent, Set<Role> roles, Boolean enabled) {
        this.name = name;
        this.surname = surname;
        this.dateBirth = dateBirth;
        this.nickname = nickname;
        this.password = password;
        this.mail = mail;
        this.idEvent = idEvent;
        this.roles = roles;
        this.enabled = enabled;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "idUser",fetch = FetchType.LAZY)
    private Set<Event> idEvent = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "user_idUser"),
            inverseJoinColumns = @JoinColumn(name = "role_idRole"))
    private Set<Role> roles;

    @JsonIgnore
    private Boolean enabled;
}
