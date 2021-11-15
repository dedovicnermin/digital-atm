//package tech.depaul.digitalatm.data.repos;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.DirtiesContext;
//import tech.depaul.digitalatm.data.model.Account;
//import tech.depaul.digitalatm.data.model.User;
//
//
//import java.util.ArrayList;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@DirtiesContext
//class UserRepositoryTest {
//
//
//    @Autowired private UserRepository userRepo;
//    @Autowired private AccountRepository accountRepo;
//
//
//    @Test
//    void addingToUserRepoWorks() {
//        final User user = User.builder()
//                .id(1L)
//                .username("user1")
//                .password("pass")
//                .firstname("jeff")
//                .lastname("bezos")
//                .role("user")
//                .account(null)
//                .build();
//
//        userRepo.save(user);
//        final User result = userRepo.findByUsername(user.getUsername()).orElse(null);
//        assertThat(result).isNotNull().isEqualTo(user);
//    }
//
//    @Test
//    void updatingUserWorks() {
//        final User user = User.builder()
//                .id(2L)
//                .username("gangsta")
//                .password("pass")
//                .firstname("g")
//                .lastname("fezos")
//                .role("user")
//                .account(new Account(1L, 22.22, 2L))
//                .build();
//
//        userRepo.save(user);
//        final User user1 = userRepo.findByUsername(user.getUsername()).orElse(null);
//        assertThat(user1).isNotNull();
//        user1.setPassword("moresecure");
//        userRepo.save(user1);
//
//
//        final User result = userRepo.findByUsername(user.getUsername()).orElse(null);
//        assertThat(result).isNotNull().hasFieldOrPropertyWithValue("password", "moresecure");
//
//    }
//
//    @Test
//    void testing() {
//
//        final Account account = Account.builder()
//                .id(3L)
//                .balance(100.00)
//                .userId(3L)
//                .build();
//
//
//        final User user = User.builder()
//                .id(3L)
//                .username("muta")
//                .password("kam")
//                .firstname("kamimi")
//                .lastname("haraza")
//                .role("user")
//                .account(account)
//                .build();
//
//        userRepo.save(user);
//
//        assertThat(accountRepo.findAll()).hasSize(1);
//        assertThat(userRepo.findByUsername(user.getUsername())).isPresent();
//    }
//
//
//
//
//
//
//
//
//
//
//}
