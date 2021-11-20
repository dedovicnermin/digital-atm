package tech.depaul.digitalatm.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.depaul.digitalatm.TestUtils;
import tech.depaul.digitalatm.config.ATMUserDetails;
import tech.depaul.digitalatm.controllers.request.DigitalATMRequest;
import tech.depaul.digitalatm.data.model.Account;
import tech.depaul.digitalatm.data.repos.AccountRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DigitalATMServiceTest {

    @Autowired
    DigitalATMService atmService;

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
    void executeWithdrawTest() {
        final DigitalATMRequest validDigitalATMRequest = TestUtils.getValidDigitalATMRequest();
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        atmService.executeWithdrawOnAccount(validDigitalATMRequest, testUserDetails);
        final Optional<Account> optionalAccount = repository.findDistinctByUserId(testUserDetails.getUserId());
        final Double expected = 90.0;
        assertThat(optionalAccount).isPresent();
        assertThat(optionalAccount.get().getBalance()).isEqualTo(expected);
    }

    @Test
    void executeDepositOnAccountTest() {
        final DigitalATMRequest validDigitalATMRequest = TestUtils.getValidDigitalATMRequest();
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        atmService.executeDepositOnAccount(validDigitalATMRequest, testUserDetails);
        final Optional<Account> optionalAccount = repository.findDistinctByUserId(testUserDetails.getUserId());
        final Double expected = 110.0;
        assertThat(optionalAccount).isPresent();
        assertThat(optionalAccount.get().getBalance()).isEqualTo(expected);
    }

    @Test
    void retrieveAccountBalanceTest() {
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        final String expected = "100.0";
        final String actual = atmService.retrieveAccountBalance(testUserDetails);
        assertThat(actual).isEqualTo(expected);
    }

}
