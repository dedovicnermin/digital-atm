package tech.depaul.digitalatm.data.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;



@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "USERS")
@RequiredArgsConstructor
public class User  {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column private String role;



}
