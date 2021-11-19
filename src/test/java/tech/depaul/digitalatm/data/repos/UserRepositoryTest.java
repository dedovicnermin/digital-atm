package tech.depaul.digitalatm.data.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import tech.depaul.digitalatm.data.model.User;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext
class UserRepositoryTest {

    @Autowired private UserRepository userRepo;

    @Test
    void retrievingByUsernameWorksAsExpected() {
        final User expected = User.builder()
                .username("aUser")
                .password("passwzrd")
                .firstname("Jay")
                .lastname("Z")
                .role("user")
                .build();
        userRepo.save(expected);
        final Optional<User> dbUser = userRepo.findByUsername(expected.getUsername());
        assertThat(dbUser).isPresent();

        assertThat(dbUser.get().getFirstname()).isEqualTo(expected.getFirstname());
        assertThat(dbUser.get().getLastname()).isEqualTo(expected.getLastname());
        assertThat(dbUser.get().getRole()).isEqualTo(expected.getRole());
    }

    @Test
    void addingToUserRepoWorks() {
        final User user = User.builder()
                .username("user1")
                .password("pass")
                .firstname("jeff")
                .lastname("bezos")
                .role("user")
                .build();

        userRepo.save(user);
        final User result = userRepo.findByUsername(user.getUsername()).orElse(null);
        assertThat(result).isNotNull().isEqualTo(user);
    }

    @Test
    void updatingUserWorks() {
        final User user = User.builder()
                .username("gangsta")
                .password("pass")
                .firstname("g")
                .lastname("fezos")
                .role("user")
                .build();

        userRepo.save(user);
        final User user1 = userRepo.findByUsername(user.getUsername()).orElse(null);
        assertThat(user1).isNotNull();
        user1.setPassword("moresecure");
        userRepo.save(user1);


        final User result = userRepo.findByUsername(user.getUsername()).orElse(null);
        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("password", "moresecure");
    }




}
