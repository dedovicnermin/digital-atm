package tech.depaul.digitalatm.components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.depaul.digitalatm.TestUtils;
import tech.depaul.digitalatm.config.ATMUserDetails;
import tech.depaul.digitalatm.data.model.Account;
import tech.depaul.digitalatm.data.repos.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DigitalAccountManagerTest {

    private static final String DANGEROUS_WITHDRAW_MESSAGE = "Withdrawing this amount would put you in the negative";

    @Autowired
    DigitalAccountManager manager;

    @Autowired
    AccountRepository repository;

    @BeforeEach
    void insert() {
        repository.save(TestUtils.getTestAccount());
    }

    @AfterEach
    void clear() {
        repository.deleteAll();
    }


    @Test
    void depositToAccountTest() {
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        final BigDecimal amount = BigDecimal.ONE;
        final Double expected = 101.00;

        manager.depositToAccount(testUserDetails,amount);

        final Optional<Account> accountOptional = repository.findDistinctByUserId(testUserDetails.getUserId());
        assertThat(accountOptional).isPresent();
        final Account account = accountOptional.get();
        assertThat(account.getBalance()).isEqualTo(expected);
    }

    @Test
    void withdrawFromAccountTest() {
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        final BigDecimal amount = BigDecimal.ONE;
        final Double expected = 99.00;

        manager.withdrawFromAccount(testUserDetails, amount);

        final Optional<Account> accountOptional = repository.findDistinctByUserId(testUserDetails.getUserId());
        assertThat(accountOptional).isPresent();
        final Account account = accountOptional.get();
        assertThat(account.getBalance()).isEqualTo(expected);

    }

    @Test
    void withdrawFromAccountThrows_whenAmountWillPutUserInNegative() {
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        final BigDecimal amount = new BigDecimal("1000000.00");

        final DigitalAtmException exception = assertThrows(DigitalAtmException.class, () -> manager.withdrawFromAccount(testUserDetails, amount));
        assertThat(exception.getMessage()).isEqualTo(DANGEROUS_WITHDRAW_MESSAGE);
    }

    @Test
    void getAccountBalanceTest() {
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        final BigDecimal expected = new BigDecimal("100.0");
        assertThat(manager.getAccountBalance(testUserDetails).doubleValue()).isEqualTo(expected.doubleValue());
    }


}
