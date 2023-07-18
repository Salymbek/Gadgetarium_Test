package peaksoft.house.gadgetariumb9.entities;

import jakarta.persistence.*;
import java.util.Collection;
import lombok.*;
import java.util.List;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.house.gadgetariumb9.enums.Role;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {//implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String address;

    private boolean isSubscription;

    private String image;

    @ElementCollection
    private List<Long> comparison;

    @ElementCollection
    private List<Long> favorite;

    @OneToMany(
        mappedBy = "user",
        cascade = {MERGE, DETACH, REFRESH, PERSIST, REMOVE})
    private List<Order> orders;

    @OneToMany(
        mappedBy = "user",
        cascade = {MERGE, DETACH, REFRESH, PERSIST})
    private List<Review> reviews;

    @OneToMany(
        mappedBy = "user",
        cascade = {MERGE, DETACH, REFRESH, PERSIST, REMOVE})
    private List<Basket> baskets;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getUsername() {
//        return this.email;
//    }
//
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}