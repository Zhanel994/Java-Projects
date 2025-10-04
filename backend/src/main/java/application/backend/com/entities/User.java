package application.backend.com.entities;

import jakarta.persistence.*;
import application.backend.com.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username", nullable = false, unique = true, length = 25)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name="name")
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "creator")
    private List<Trip> tripsCreated;

    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private List<Trip> tripsParticipated = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }
}
