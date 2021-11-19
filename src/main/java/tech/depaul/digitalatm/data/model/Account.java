package tech.depaul.digitalatm.data.model;

import lombok.*;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ACCOUNTS")
@RequiredArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 2)
    @Setter
    private Double balance;

    @Column(nullable = false, unique = true)
    private Long userId;




}
