package be.bt.businesstraining.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data
@Entity()
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long idUser;
    @Column(name = "NAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String name;
    @Column(name = "LASTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String surname;
    @Column(name = "DATEBIRTH", length = 50)
    @NotNull
    @Size(min = 10, max = 50)
    private String dateBirth;
    @Column(name = "NICKNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String nickname;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;
    @Column(name = "MAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String mail;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "idUser", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;


    @JsonIgnore
    @OneToMany(mappedBy = "idUser",fetch = FetchType.LAZY)
    private Set<Event> idEvent = new HashSet<>();

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(joinColumns = @JoinColumn(name = "user_idUser"),
//            inverseJoinColumns = @JoinColumn(name = "role_idRole"))
//    private Set<Role> roles;

    @JsonIgnore
    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;
    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    @JsonIgnore
    private Date lastPasswordResetDate;

}
