//package tech.depaul.digitalatm.data.repos;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import tech.depaul.digitalatm.data.model.Account;
//
//import java.util.ArrayList;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//class AccountRepositoryTest {
//
//    @Autowired private AccountRepository accountRepo;
//
//    @Test
//    void givenAccountWithNullTransactions_canBeSaved() {
//        final Account acc = Account.builder()
//                .id(null)
//                .userId(1L)
//                .balance(0.0)
//                .build();
//        accountRepo.save(acc);
//        System.out.println(acc.getId());
//
//        final Account result = accountRepo.findById(acc.getId()).orElse(null);
//        assertThat(result).isNotNull().isEqualTo(acc);
//    }
//
//    @Test
//    void givenAccountWithEmptyTList_canBeSaved() {
//        final Account acc = Account.builder()
//                .id(null)
//                .userId(2L)
//                .balance(0.0)
//                .build();
//        accountRepo.save(acc);
//        System.out.println(acc.getId());
//        final Account result = accountRepo.findById(acc.getId()).orElse(null);
//        assertThat(result).isNotNull().isEqualTo(acc);
//    }
//
//
//
//}
