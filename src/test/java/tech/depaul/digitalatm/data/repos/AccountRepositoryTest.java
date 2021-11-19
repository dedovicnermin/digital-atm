package tech.depaul.digitalatm.data.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.depaul.digitalatm.data.model.Account;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired private AccountRepository accountRepo;

    @Test
    void addingAccountNoId_willAutoGenerate() {
        final Account acc = Account.builder()
                .id(null)
                .userId(1L)
                .balance(0.0)
                .build();
        accountRepo.save(acc);

        final Account result = accountRepo.findById(acc.getId()).orElse(null);
        System.out.println(acc.getId());
        assertThat(result).isNotNull().isEqualTo(acc);
    }

    @Test
    void retrievingAccountById() {
        final Account acc = Account.builder()
                .id(null)
                .userId(2L)
                .balance(100D)
                .build();
        accountRepo.save(acc);
        final Optional<Account> actual = accountRepo.findDistinctByUserId(acc.getUserId());
        assertThat(actual).isPresent();
        assertThat(actual).contains(acc);

    }

    @Test
    void retrievingAccountByUserId() {
        final Account acc = Account.builder()
                .id(null)
                .userId(3L)
                .balance(1.0)
                .build();
        accountRepo.save(acc);
        final Optional<Account> actual = accountRepo.findById(acc.getId());
        assertThat(actual).isPresent();
        assertThat(actual).contains(acc);
    }


    @Test
    void updatingAccountBalance() {
        final Account account = Account.builder()
                .id(null)
                .userId(4L)
                .balance(100.00)
                .build();

        accountRepo.save(account);
        final Optional<Account> accountOptional = accountRepo.findById(account.getId());
        assertThat(accountOptional).isPresent();

        final Account account1 = accountOptional.get();
        account1.setBalance(account.getBalance() + 1);
        accountRepo.save(account1);
        final Optional<Account> actual = accountRepo.findById(account.getId());
        assertThat(actual).isPresent();
        final Double balance = actual.get().getBalance();
        assertThat(balance).isEqualTo(101.00);
    }




}
